# Behavioral Design Patterns

## Observer

- Used to notify multiple objects whenever a particular objct changes in state
- When there is a one-to-many relationship
- Subjects and Observers are loosely coupled
- Usually gets implements where Observers are loosely coupled
- Also known as Publisher-Subscriber or Pub-Sub

## Structure

```java

interface Observer {
    void update(String update);
}
class ConcreteObserverOne {
    public void update(){
        ...
    }
}
class ConcreteObserverTwo {
    private String update;
    public void update(){
        this.update = update;
    }
    private void display(){
        System.out.println(update);
    }
}
interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyAll();
}
```
