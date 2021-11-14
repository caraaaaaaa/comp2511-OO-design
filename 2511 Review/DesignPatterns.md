
# <center> Comp2511 Design Patterns </center>

A design pattern is a tried solution to a commonly recurring problem.

Original use comes from a set of 250 patterns formulated by Christopher Alexander et al for architectural (building) design.

Every pattern has
- A short name
- A description of the context
- A description of the problem
- A prescription for a solution

In software engineering, a design pattern is a general repeatable solution to a commonly occurring problem in software design.

A design pattern is
- Represents a template for how to solve a problem
- Captures design expertise and enables this knowledge to be transferred and reused
- Provide shared vocabularies, improve communications and eases implementation
- Is not a finished solution, they give you general solutions to design problems

Design Patterns Categories:
- Behavioural Patterns
    - Iterator Pattern: 遍历整个collection去做一些事情
    - Observer Pattern: 一个subject 一个observer 当subject发生变化时，notify在list里面的observer
    - Strategy Pattern: 将所有的算法封装在一个方法，在执行过程中做出选择
    - Visitor Pattern:  两个interface 一个 visitor 一个visiable 去 visit所有东西
- Structural Patterns
    - Adapter Pattern: 将两种不适配的东西进行适配(例如json文件与xml文件转换)
    - Composite Pattern: Leaf 和包含Leaf的Component，组合处理
    - Decorator Pattern: 在不改变原有的基础上累加新东西（类似于点奶茶额外加料）
    - Template Pattern: 将固定的东西在abstract里面写好 不固定的在subclasses里面override
- Creational Patterns
    - Factory Method: 在父类中提供一个创建对象的方法，允许子类决定实例化对象的类型。
    - Abstract Factory Pattern: 创建一系列相关的对象，而无需指定其具体类。
    - Builder Pattern: 分步骤创建复杂对象。
    - Singleton Pattern: 保证一个类只有一个实例， 并提供一个访问该实例的全局节点。