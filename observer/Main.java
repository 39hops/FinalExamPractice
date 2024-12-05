package observer;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void add(Observer o);
    void remove(Observer o);
    void notifyObservers();
}
interface Observer {
    void update(String weather);
}
class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String weather;
    public void add(Observer o){
        observers.add(o);
    }
    public void remove(Observer o){
        observers.remove(o);
    }
    public void notifyObservers(){
        for (Observer o : observers){
            o.update(weather);
        }
    }
    public void setWeather(String w){
        this.weather = w;
        notifyObservers();
    }
}
class PhoneDisplay implements Observer {
    private String weather;
    public void update(String weather){
        this.weather = weather;
        display();
    }
    private void display(){
        System.out.println("Phone: Weather: " + weather);
    }
}
class TVDisplay implements Observer {
    private String weather;
    public void update(String weather){
        this.weather = weather;
        display();
    }
    private void display(){
        System.out.println("TV: Weather: " + weather);
    }
}
class Main {
    public static void main(String[] args) {
        WeatherStation ws = new WeatherStation();
        Observer phone = new PhoneDisplay();
        Observer tv = new TVDisplay();
        ws.add(phone);
        ws.add(tv);
        ws.setWeather("Sunny");
    }
}