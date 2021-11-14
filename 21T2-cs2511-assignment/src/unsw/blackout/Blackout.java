package unsw.blackout;

import java.text.Collator;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import static java.time.temporal.ChronoUnit.MINUTES;

import org.json.JSONArray;
import org.json.JSONObject;



public class Blackout {
    public ArrayList<Satellite> satelliteList = new ArrayList<Satellite>();
    public ArrayList<Device> deviceList = new ArrayList<Device>();

    public LocalTime currenttime = LocalTime.of(0, 0);


    
    /** 
     * create new device
     * @param id
     * @param type
     * @param position
     */
    public void createDevice(String id, String type, double position) {
        Device newDevice = new Device(id, type, position);

        for (int i = 0; i < satelliteList.size(); i++) {
            if (MathsHelper.satelliteIsVisibleFromDevice(((Satellite) satelliteList.get(i)).getPosition(), ((Satellite) satelliteList.get(i)).getHeight(), position)) {
                ((Satellite) satelliteList.get(i)).addpossibleConnections(id, ((Device) deviceList.get(i)).getType());
            }
        }

        // check if same device ID is already in device list
        int flag = 1;
        for (int i = 0; i < deviceList.size(); i++) {
            if (((Device) deviceList.get(i)).getId().equals(id)) {;
                flag = 0;
            }
        }
        if (flag == 1) {
            deviceList.add(newDevice);
        }
        
        // sort device by device ID
        deviceArraySortById(deviceList);
    }



    
    /** 
     * create new satellite
     * @param id
     * @param type
     * @param height
     * @param position
     */
    public void createSatellite(String id, String type, double height, double position) {

        if (type.equals("SpaceXSatellite")) {
            Satellite newSpace = new SpaceXSatellite(id, height, position);
            for (int i = 0; i < deviceList.size(); i++) {
                if (MathsHelper.satelliteIsVisibleFromDevice(position, height, ((Device) deviceList.get(i)).getPosition())) {
                    newSpace.addpossibleConnections(((Device) deviceList.get(i)).getId(), ((Device) deviceList.get(i)).getType());
                } 
            }
            satelliteList.add(newSpace);
        } else if (type.equals("BlueOriginSatellite")) {
            Satellite newBlue = new BlueOriginSatellite(id, height, position);
            for (int i = 0; i < deviceList.size(); i++) {
                if (MathsHelper.satelliteIsVisibleFromDevice(position, height, ((Device) deviceList.get(i)).getPosition())) {
                    newBlue.addpossibleConnections(((Device) deviceList.get(i)).getId(), ((Device) deviceList.get(i)).getType());
                } 
            }
            satelliteList.add(newBlue);
        } else if (type.equals("SovietSatellite")) {
            Satellite newSoviet = new SovietSatellite(id, height, position);
            for (int i = 0; i < deviceList.size(); i++) {
                if (MathsHelper.satelliteIsVisibleFromDevice(position, height, ((Device) deviceList.get(i)).getPosition())) {
                    newSoviet.addpossibleConnections(((Device) deviceList.get(i)).getId(), ((Device) deviceList.get(i)).getType());
                } 
            }
            satelliteList.add(newSoviet);
        } else {
            Satellite newNasa = new NasaSatellite(id, height, position);
            for (int i = 0; i < deviceList.size(); i++) {
                if (MathsHelper.satelliteIsVisibleFromDevice(position, height, ((Device) deviceList.get(i)).getPosition())) {
                    newNasa.addpossibleConnections(((Device) deviceList.get(i)).getId(), ((Device) deviceList.get(i)).getType());
                } 
            }
            satelliteList.add(newNasa);
        }

        // sort satellite by Satellite ID
        satelliteArraySortById(satelliteList);
    }



    
    /** 
     * set devive activation period
     * @param deviceId
     * @param start
     * @param durationInMinutes
     */
    public void scheduleDeviceActivation(String deviceId, LocalTime start, int durationInMinutes) {
        LocalTime end = start.plus(Duration.of(durationInMinutes, ChronoUnit.MINUTES));
        for (int i = 0; i < deviceList.size(); i++) {
            if (deviceId.equals(((Device) deviceList.get(i)).getId())) {;
                ((Device) deviceList.get(i)).addActivationPeriods(start, end);
            }
        }
    }



    
    /** 
     * @param id
     */
    public void removeSatellite(String id) {
        for (int i = 0; i < satelliteList.size(); i++) {
            if (id.equals(((Satellite) satelliteList.get(i)).getId())) {;
                satelliteList.remove(i);
            }
        }

    }


    
    /** 
     * @param id
     */
    public void removeDevice(String id) {
        for (int i = 0; i < deviceList.size(); i++) {
            if (id.equals(((Device) deviceList.get(i)).getId())) {;
                deviceList.remove(i);
            }
        }

    }

    
    /** 
     * @param id
     * @param newPos
     */
    public void moveDevice(String id, double newPos) {
        for (int i = 0; i < deviceList.size(); i++) {
            if (id.equals(((Device) deviceList.get(i)).getId())) {;
                ((Device) deviceList.get(i)).setPosition(newPos);
            }
        }
        for (int i = 0; i < satelliteList.size(); i++) {
            if (((Satellite) satelliteList.get(i)).getPossibleConnections().contains(id)) {
                if (!MathsHelper.satelliteIsVisibleFromDevice(((Satellite) satelliteList.get(i)).getPosition(), ((Satellite) satelliteList.get(i)).getHeight(), newPos)) {
                    ((Satellite) satelliteList.get(i)).removepossibleConnections(id);
                }
            }
        }
    }



    
    /** 
     * @return JSONObject
     */
    public JSONObject showWorldState() {
        JSONObject result = new JSONObject();
        JSONArray devices = new JSONArray();
        JSONArray satellites = new JSONArray();




        for (int i = 0; i < satelliteList.size(); i++) {
            JSONObject newS = new JSONObject(satelliteList.get(i));
            satellites.put(newS);
        }

        for (int i = 0; i < deviceList.size(); i++) {
            JSONObject newD = new JSONObject(deviceList.get(i));
            devices.put(newD);
        }


        result.put("devices", devices);
        result.put("satellites", satellites);

        
        result.put("currentTime", this.currenttime);

        return result;
    }

    
    /** 
     * @param tickDurationInMinutes
     */
    public void simulate(int tickDurationInMinutes) {


        for (int minutes = 0; minutes < tickDurationInMinutes; minutes++) {
            // this.currenttime = this.currenttime.plus(Duration.of(minutes, ChronoUnit.MINUTES));

            // update satellite position
            // for (int i = 0; i < minutes; i++) {
            for (int i = 0; i < satelliteList.size(); i++) {
                satelliteList.get(i).updatePosition(minutes);
            }

            // update possible connections
            for (int i = 0; i < satelliteList.size(); i++) {
                for (int j = 0; j < deviceList.size(); j++) {
                    if (!MathsHelper.satelliteIsVisibleFromDevice(((Satellite) satelliteList.get(i)).getPosition(), ((Satellite) satelliteList.get(i)).getHeight(), ((Device) deviceList.get(j)).getPosition())) {
                        ((Satellite) satelliteList.get(i)).removepossibleConnections(((Device) deviceList.get(j)).getId());
                        deviceList.get(j).setIsConnected(false);
                        // for (Connections c: satelliteList.get(i).getConnections()) {
                        //     if (c.getDeviceId().equals(deviceList.get(j).getId())) {
                        //         c.setEndTime(LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                        //     }
                        // }
                    }
                    if (MathsHelper.satelliteIsVisibleFromDevice(((Satellite) satelliteList.get(i)).getPosition(), ((Satellite) satelliteList.get(i)).getHeight(), ((Device) deviceList.get(j)).getPosition())) {
                        ((Satellite) satelliteList.get(i)).addpossibleConnections(((Device) deviceList.get(j)).getId(), ((Device) deviceList.get(j)).getType());
                    }
                }
            }

            //      

            
            for (int i = 0; i < satelliteList.size(); i++) {
                if (satelliteList.get(i).getType().equals("SpaceXSatellite")) {
                    int numall = 0;
                    for (String s:satelliteList.get(i).getPossibleConnections()) {
                        for (int j = 0; j < deviceList.size(); j++) {
                            if ((deviceList.get(j).getId().equals(s))  && (deviceList.get(j).getType().equals("HandheldDevice"))) {
                                for (ActivationPeriods h: deviceList.get(j).getActivationPeriods()) {
                                    if (checkTimePeriod(LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)), h.getStartTime(), h.getEndTime())) {
                                        // System.out.println("aaaaaaaa");
                                        int flag = 0;
                                        for (Connections c: satelliteList.get(i).getConnections()) {
                                            // int conpare = (int) MINUTES.between(c.getEndTime(), LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                            if ((h.getStartTime().equals(c.getStartTime())) && (c.getDeviceId().equals(deviceList.get(j).getId()))) {
                                                c.setEndTime(LocalTime.of(0, 0).plus(Duration.of(minutes+1, ChronoUnit.MINUTES)));
                                                c.setMinutesActive(c.getMinutesActive()+1);
                                                flag = 1;
                                            }
                                        }
                                        if (flag == 0) {
                                            satelliteList.get(i).addConnections(deviceList.get(j).getId(), LocalTime.of(0, 0).plus(Duration.of(minutes+1, ChronoUnit.MINUTES)), -1, satelliteList.get(i).getId(),  LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                            deviceList.get(j).setIsConnected(true);
                                            numall = numall + 1;
                                        }
                                        
                                    } else {
                                        deviceList.get(j).setIsConnected(false);
                                        // for (Connections c: satelliteList.get(i).getConnections()) {
                                        //     if ((c.getDeviceId().equals(deviceList.get(j).getId())) && (c.getEndTime().equals(null))) {
                                        //         c.setEndTime(LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                        //     }
                                        // }
                                    }
                                }
                            }
                        }
                    }
                } else if (satelliteList.get(i).getType().equals("BlueOriginSatellite")) {
                    int numLaptop = 0;
                    int numDesktop = 0;
                    int numall = 0;
                    for (String s:satelliteList.get(i).getPossibleConnections()) {
                        for (int j = 0; j < deviceList.size(); j++) {
                            if ((deviceList.get(j).getId().equals(s))) {
                                for (ActivationPeriods h: deviceList.get(j).getActivationPeriods()) {
                                    if (checkTimePeriod(LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)), h.getStartTime(), h.getEndTime())) {
                                        // System.out.println("aaaaaaaa");
                                        if ((numLaptop<=5) && (numDesktop<=2) && (numall<=10)) {
                                            int flag = 0;
                                            for (Connections c: satelliteList.get(i).getConnections()) {
                                                // int conpare = (int) MINUTES.between(c.getEndTime(), LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                                if ((h.getStartTime().equals(c.getStartTime())) && (c.getDeviceId().equals(deviceList.get(j).getId()))) {
                                                    c.setEndTime(LocalTime.of(0, 0).plus(Duration.of(minutes+1, ChronoUnit.MINUTES)));
                                                    c.setMinutesActive(c.getMinutesActive()+1);
                                                    flag = 1;
                                                }
                                            }
                                            if (flag == 0) {
                                                if (deviceList.get(j).getType().equals("LaptopDevice")) {
                                                    numLaptop = numLaptop+1;
                                                    numall = numall + 1;
                                                    satelliteList.get(i).addConnections(deviceList.get(j).getId(), LocalTime.of(0, 0).plus(Duration.of(minutes+1, ChronoUnit.MINUTES)), -2, satelliteList.get(i).getId(),  LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                                    deviceList.get(j).setIsConnected(true);
                                                } else if (deviceList.get(j).getType().equals("DesktopDevice")) {
                                                    numDesktop = numDesktop +1;
                                                    numall = numall + 1;
                                                    satelliteList.get(i).addConnections(deviceList.get(j).getId(), LocalTime.of(0, 0).plus(Duration.of(minutes+1, ChronoUnit.MINUTES)), -5, satelliteList.get(i).getId(),  LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                                    deviceList.get(j).setIsConnected(true);
                                                } else {
                                                    satelliteList.get(i).addConnections(deviceList.get(j).getId(), LocalTime.of(0, 0).plus(Duration.of(minutes+1, ChronoUnit.MINUTES)), -1, satelliteList.get(i).getId(),  LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                                    deviceList.get(j).setIsConnected(true);
                                                    numall = numall + 1;
                                                }
                                            }
                                        }
                                    } else {
                                        deviceList.get(j).setIsConnected(false);
                                        // for (Connections c: satelliteList.get(i).getConnections()) {
                                        //     if ((c.getDeviceId().equals(deviceList.get(j).getId())) && (c.getEndTime().equals(null))) {
                                        //         c.setEndTime(LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                        //     }
                                            
                                            
                                        // }
                                    }
                                }
                            }
                        }
                    }
                } else if (satelliteList.get(i).getType().equals("NasaSatellite")) {
                    int numall = 0;
                    for (String s:satelliteList.get(i).getPossibleConnections()) {
                        for (int j = 0; j < deviceList.size(); j++) {
                            if ((deviceList.get(j).getId().equals(s))) {
                                for (ActivationPeriods h: deviceList.get(j).getActivationPeriods()) {
                                    if (checkTimePeriod(LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)), h.getStartTime(), h.getEndTime())) {
                                        // System.out.println("aaaaaaaa");
                                        int flag = 0;
                                        for (Connections c: satelliteList.get(i).getConnections()) {
                                            // int conpare = (int) MINUTES.between(c.getEndTime(), LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                            if ((h.getStartTime().equals(c.getStartTime())) && (c.getDeviceId().equals(deviceList.get(j).getId()))) {
                                                c.setEndTime(LocalTime.of(0, 0).plus(Duration.of(minutes+1, ChronoUnit.MINUTES)));
                                                c.setMinutesActive(c.getMinutesActive()+1);
                                                flag = 1;
                                            }
                                        }
                                        if (flag == 0) {
                                            if (numall<=6) {
                                                satelliteList.get(i).addConnections(deviceList.get(j).getId(), LocalTime.of(0, 0).plus(Duration.of(minutes+1, ChronoUnit.MINUTES)), -10, satelliteList.get(i).getId(),  LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                                deviceList.get(j).setIsConnected(true);
                                                numall = numall+1;
                                            // } else {
                                            //     if ((deviceList.get(j).getPosition() >= 30) && (deviceList.get(j).getPosition() <= 40)){
                                                    
    
                                            //     }
                                            }
                                        }
                                    } else {
                                        deviceList.get(j).setIsConnected(false);
                                        // for (Connections c: satelliteList.get(i).getConnections()) {
                                        //     if ((c.getDeviceId().equals(deviceList.get(j).getId())) && (c.getEndTime().equals(null))) {
                                        //         c.setEndTime(LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                        //     }
                                            
                                            
                                        // }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (String s:satelliteList.get(i).getPossibleConnections()) {
                        int numall = 0;
                        for (int j = 0; j < deviceList.size(); j++) {
                            if ((deviceList.get(j).getId().equals(s))) {
                                for (ActivationPeriods h: deviceList.get(j).getActivationPeriods()) {
                                    if (checkTimePeriod(LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)), h.getStartTime(), h.getEndTime())) {
                                        // System.out.println("aaaaaaaa");
                                        int flag = 0;
                                        for (Connections c: satelliteList.get(i).getConnections()) {
                                            // int conpare = (int) MINUTES.between(c.getEndTime(), LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                            if ((h.getStartTime().equals(c.getStartTime())) && (c.getDeviceId().equals(deviceList.get(j).getId()))) {
                                                c.setEndTime(LocalTime.of(0, 0).plus(Duration.of(minutes+1, ChronoUnit.MINUTES)));
                                                c.setMinutesActive(c.getMinutesActive()+1);
                                                flag = 1;
                                            }
                                        }
                                        if (flag == 0) {
                                            if (numall <= 9) {
                                                satelliteList.get(i).addConnections(deviceList.get(j).getId(), LocalTime.of(0, 0).plus(Duration.of(minutes+1, ChronoUnit.MINUTES)), 0, satelliteList.get(i).getId(),  LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                                deviceList.get(j).setIsConnected(true);
                                                numall = numall+1;
                                            }
                                        }
                                        
                                    } else {
                                        deviceList.get(j).setIsConnected(false);
                                        // for (Connections c: satelliteList.get(i).getConnections()) {
                                        //     if ((c.getDeviceId().equals(deviceList.get(j).getId())) && (c.getEndTime().equals(null))) {
                                        //         c.setEndTime(LocalTime.of(0, 0).plus(Duration.of(minutes, ChronoUnit.MINUTES)));
                                        //     }
                                            
                                            
                                        // }

                                    }
                                }
                            }
                        }
                    }
                }

            }


        }


        // update current time
        this.currenttime = LocalTime.of(0, 0).plus(Duration.of(tickDurationInMinutes, ChronoUnit.MINUTES));

    }

    public boolean checkTimePeriod(LocalTime current, LocalTime start, LocalTime end) {
        if ((start.compareTo(current) <= 0) && (end.compareTo(current) >= 0)) {
            return true;
        } else {
            return false;
        }
        

    }

    
    /** 
     * @param list
     * @return ArrayList<Device>
     */
    public ArrayList<Device> deviceArraySortById(ArrayList<Device> list){
        if(list==null || list.size()==0){
            return null;
        }
        Map<String, Device> map = new HashMap<String, Device>();
        String idnames[] = new String[list.size()];
        for(int i=0;i<list.size();i++){
            String idname = list.get(i).getId();
            idnames[i] = idname;
            map.put(idname, list.get(i));
        }

        Comparator<Object> comparator = Collator.getInstance(Locale.ENGLISH);
        Arrays.sort(idnames, comparator);
        
        list.clear();
        for (String idn: idnames){
            if (map.containsKey(idn)) {
                list.add(map.get(idn));
            }
        }
        return list;
    }
    
    
    /** 
     * @param list
     * @return ArrayList<Satellite>
     */
    public ArrayList<Satellite> satelliteArraySortById(ArrayList<Satellite> list){
        if(list==null || list.size()==0){
            return null;
        }
        Map<String, Satellite> map = new HashMap<String, Satellite>();
        String names[] = new String[list.size()];
        for (int i=0; i<list.size(); i++){
            String name = list.get(i).getId();
            names[i] = name;
            map.put(name, list.get(i));
        }

        Comparator<Object> comparator = Collator.getInstance(Locale.ENGLISH);
        Arrays.sort(names, comparator);
        
        list.clear();
        for (String name : names){
            if (map.containsKey(name)) {
                list.add(map.get(name));
            }
        }
        return list;
    }

}
