from __future__ import annotations
from abc import ABC, abstractmethod

class Subject(ABC):
    @abstractmethod
    def add(self, o: Observer) -> None:
        pass
    
    @abstractmethod 
    def remove(self, o: Observer) -> None:
        pass
    
    @abstractmethod
    def notify(self) -> None:
        pass

class WeatherStation(Subject):
    weather: str = None
    observers = []
    
    def add(self, observer: Observer) -> None:
        self.observers.append(observer)

class Observer(ABC):
    @abstractmethod
    def update(self, subject: Subject) -> None:
        pass