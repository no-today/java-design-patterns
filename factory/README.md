## 工厂模式

客户端不直接与产品耦合，而是与工厂打交道。客户只需要告诉工厂自己需要什么，而不用管产品是如何制造出来的。

### 适合应用场景

无法预知对象确切类别及其依赖关系时，可使用工厂方法。

工厂方法将创建产品的代码与实际使用产品的代码分离，从而能在不影响其他代码的情况下扩展产品创建部分的代码。

例如，如果需要新加一种新产品，只需要开发新的产品制造者子类，然后重写工厂方法，客户端代码只需新增，无需修改。