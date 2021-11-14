# <center> Adaptor Pattern </center>

## Intent
- "Convert the interface of a class into another interface clients expect.

Adapter lets classes work together that couldn't otherwise because of incompatible
interfaces."
- The adapter pattern allows the interface of an existing class to be used as another interface, suitable for a client class.
- The adapter pattern is often used to make existing classes (APIs) work with a client class
without modifying their source code.
- The adapter class maps / joins functionality of two different types / interfaces.
- The adapter patter offers a wrapper around an existing useful class, such that a client class can use functionality of the existing class.
- The adapter pattern do not offer additional functionality.

~~~java
interface LightingPhone {
    void recharge();
    void useLighting();
} 

interface TypeCPhone {
    void recharge();
    void useTypeC();
}
~~~

~~~java
class Iphone implements LightingPhone {
    private boolean connector;

    @override
    public void useLighting() {
        connector = true;
        System.out.println("Lighting Connected");
    }

    @override
    public void recharge() {
        if (connector) {
            System.out.println("Recharge started"); 
            System.out.println("Recharge finished");     
        } else {
            System.out.println("Connect Lighting first");
        }
    }
}

~~~java
class Android implements TypeCPhone {
    private boolean connector;

    @override
    public void useTypeC() {
        connector = true;
        System.out.println("TypeC Connected");
    }

    @override
    public void recharge() {
        if (connector) {
            System.out.println("Recharge started"); 
            System.out.println("Recharge finished");     
        } else {
            System.out.println("Connect TypeC first");
        }
    }
}
~~~

~~~java
class LightingToTypeCAdapter implements TypeCPhone {
    private final LightingPhone lightingphone;

    @override
    public void useTypeC() {
        System.out.println("TypeC Connected");
        lightingphone.useLighting();
    }

    @override
    public void recharge() {
        lightingphone.recharge();
    }

}