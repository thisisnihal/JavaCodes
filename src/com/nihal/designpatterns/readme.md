Here's Java code for each of the following **design patterns**, using **real-world-inspired examples**:

---

## ✅ 1. **Factory Method Pattern**

### 🚚 Example: Logistics

```java
abstract class Transport {
    public abstract void deliver();
}

class Truck extends Transport {
    public void deliver() {
        System.out.println("Deliver by land in a box.");
    }
}

class Ship extends Transport {
    public void deliver() {
        System.out.println("Deliver by sea in a container.");
    }
}

abstract class Logistics {
    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }

    protected abstract Transport createTransport();
}

class RoadLogistics extends Logistics {
    protected Transport createTransport() {
        return new Truck();
    }
}

class SeaLogistics extends Logistics {
    protected Transport createTransport() {
        return new Ship();
    }
}

public class FactoryMethodDemo {
    public static void main(String[] args) {
        Logistics logistics = new RoadLogistics();
        logistics.planDelivery();

        logistics = new SeaLogistics();
        logistics.planDelivery();
    }
}
```

---

## ✅ 2. **Singleton Pattern**

### 🛠️ Example: Logger

```java
public class Logger {
    private static Logger instance;

    private Logger() {
        System.out.println("Logger initialized.");
    }

    public static synchronized Logger getInstance() {
        if (instance == null)
            instance = new Logger();
        return instance;
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }

    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Application started.");
    }
}
```

---

## ✅ 3. **Builder Pattern**

### 🧱 Example: Building a Complex `User` Object

```java
class User {
    private String name;
    private int age;
    private String email;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }

    public static class Builder {
        private String name;
        private int age;
        private String email;

        public Builder(String name) {
            this.name = name;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public void print() {
        System.out.println("User: " + name + ", Age: " + age + ", Email: " + email);
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        User user = new User.Builder("Alice")
                        .age(30)
                        .email("alice@example.com")
                        .build();
        user.print();
    }
}
```

---

## ✅ 4. **Prototype Pattern**

### 🧬 Example: Cloning a Document

```java
interface Prototype extends Cloneable {
    Prototype clone();
}

class Document implements Prototype {
    private String text;

    public Document(String text) {
        this.text = text;
    }

    public void show() {
        System.out.println("Document: " + text);
    }

    public Prototype clone() {
        return new Document(this.text);
    }
}

public class PrototypeDemo {
    public static void main(String[] args) {
        Document doc1 = new Document("Original Document");
        Document doc2 = (Document) doc1.clone();

        doc1.show();
        doc2.show();
    }
}
```

---

## ✅ 5. **Adapter Pattern**

### 🔌 Example: Using a Square Peg with a Round Hole

```java
class RoundHole {
    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public boolean fits(RoundPeg peg) {
        return this.radius >= peg.getRadius();
    }
}

class RoundPeg {
    private double radius;

    public RoundPeg(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}

class SquarePeg {
    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }
}

class SquarePegAdapter extends RoundPeg {
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        super(peg.getWidth() * Math.sqrt(2) / 2);
        this.peg = peg;
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        SquarePeg squarePeg = new SquarePeg(7);

        RoundPeg adapter = new SquarePegAdapter(squarePeg);
        System.out.println("Fits? " + hole.fits(adapter));
    }
}
```

---

## ✅ 6. **Decorator Pattern**

### 🎨 Example: Text Formatting

```java
interface Text {
    String render();
}

class PlainText implements Text {
    public String render() {
        return "Hello, World!";
    }
}

class BoldDecorator implements Text {
    private Text text;

    public BoldDecorator(Text text) {
        this.text = text;
    }

    public String render() {
        return "<b>" + text.render() + "</b>";
    }
}

class ItalicDecorator implements Text {
    private Text text;

    public ItalicDecorator(Text text) {
        this.text = text;
    }

    public String render() {
        return "<i>" + text.render() + "</i>";
    }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        Text text = new PlainText();
        text = new BoldDecorator(new ItalicDecorator(text));
        System.out.println(text.render());
    }
}
```

---

## ✅ 7. **Facade Pattern**

### 🏠 Example: Home Theater System

```java
class DVDPlayer {
    public void on() { System.out.println("DVD Player ON"); }
    public void play(String movie) { System.out.println("Playing " + movie); }
}

class Projector {
    public void on() { System.out.println("Projector ON"); }
    public void wideScreenMode() { System.out.println("Wide screen mode ON"); }
}

class TheaterLights {
    public void dim() { System.out.println("Lights dimmed"); }
}

class HomeTheaterFacade {
    private DVDPlayer dvd;
    private Projector projector;
    private TheaterLights lights;

    public HomeTheaterFacade(DVDPlayer dvd, Projector projector, TheaterLights lights) {
        this.dvd = dvd;
        this.projector = projector;
        this.lights = lights;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim();
        projector.on();
        projector.wideScreenMode();
        dvd.on();
        dvd.play(movie);
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(
            new DVDPlayer(), new Projector(), new TheaterLights()
        );
        homeTheater.watchMovie("Inception");
    }
}
```

---

## ✅ 8. **Strategy Pattern**

### 🎮 Example: Character Attacks

```java
interface AttackStrategy {
    void attack();
}

class SwordAttack implements AttackStrategy {
    public void attack() {
        System.out.println("Attacking with a sword!");
    }
}

class BowAttack implements AttackStrategy {
    public void attack() {
        System.out.println("Shooting an arrow!");
    }
}

class Character {
    private AttackStrategy strategy;

    public void setAttackStrategy(AttackStrategy strategy) {
        this.strategy = strategy;
    }

    public void attack() {
        strategy.attack();
    }
}

public class StrategyDemo {
    public static void main(String[] args) {
        Character warrior = new Character();
        warrior.setAttackStrategy(new SwordAttack());
        warrior.attack();

        warrior.setAttackStrategy(new BowAttack());
        warrior.attack();
    }
}
```

---

## ✅ 9. **Observer Pattern**

### 🔔 Example: News Agency

```java
import java.util.*;

interface Observer {
    void update(String news);
}

interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String news;

    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(news);
        }
    }
}

class NewsChannel implements Observer {
    private String name;

    public NewsChannel(String name) {
        this.name = name;
    }

    public void update(String news) {
        System.out.println(name + " received: " + news);
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        NewsChannel channel1 = new NewsChannel("Channel A");
        NewsChannel channel2 = new NewsChannel("Channel B");

        agency.addObserver(channel1);
        agency.addObserver(channel2);

        agency.setNews("Breaking News!");
    }
}
```

---

## ✅ 10. **State Pattern**

### 🎮 Example: Document Workflow

```java
interface State {
    void handleRequest(Document doc);
}

class DraftState implements State {
    public void handleRequest(Document doc) {
        System.out.println("Document in Draft state.");
        doc.setState(new ModerationState());
    }
}

class ModerationState implements State {
    public void handleRequest(Document doc) {
        System.out.println("Document in Moderation state.");
        doc.setState(new PublishedState());
    }
}

class PublishedState implements State {
    public void handleRequest(Document doc) {
        System.out.println("Document already Published.");
    }
}

class Document {
    private State state = new DraftState();

    public void setState(State state) {
        this.state = state;
    }

    public void request() {
        state.handleRequest(this);
    }
}

public class StateDemo {
    public static void main(String[] args) {
        Document doc = new Document();
        doc.request(); // Draft -> Moderation
        doc.request(); // Moderation -> Published
        doc.request(); // Published
    }
}
```

---

