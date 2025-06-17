Great follow-up — now let's contrast **fields (variables)** with **methods** in Java, especially with inheritance and polymorphism.

---

## ✅ Methods in Java: *Overriding* vs. Shadowing

In Java, **methods are dynamically dispatched**, meaning:

* The method that's called is determined by the **runtime type of the object**, not the compile-time type of the reference.
* This is **true polymorphism**, unlike with variables.

### 🔁 Method Overriding Example

```java
class Parent {
    String message() {
        return "Hello from Parent";
    }
}

class Child extends Parent {
    @Override
    String message() {
        return "Hello from Child";
    }
}

public class Test {
    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.message());  // Hello from Child
    }
}
```

### ✅ Key Differences from Variable Shadowing

| Aspect             | Fields (Variables) | Methods                     |
| ------------------ | ------------------ | --------------------------- |
| Dispatch type      | **Static**         | **Dynamic** (runtime)       |
| Based on reference | Yes                | No (based on actual object) |
| Can override?      | ❌ No (shadow only) | ✅ Yes (with `@Override`)    |
| Polymorphic?       | ❌ No               | ✅ Yes                       |

---

### ❗ Note on `static` Methods

Just like static variables, **static methods** are **not overridden**, but *hidden*.

```java
class Parent {
    static void greet() {
        System.out.println("Static greet from Parent");
    }
}

class Child extends Parent {
    static void greet() {
        System.out.println("Static greet from Child");
    }
}

public class Test {
    public static void main(String[] args) {
        Parent p = new Child();
        p.greet(); // Static greet from Parent
    }
}
```

* Static methods behave like static variables: **resolved by reference type**, not actual object.

---

## TL;DR

* **Instance methods** are overridden → dynamic dispatch.
* **Fields and static methods/variables** are shadowed (or hidden) → static resolution by reference/class.

Let me know if you want to see how access modifiers or `super` affect this behavior.


Absolutely — understanding the **difference between reference type and object type** is the key to mastering how Java behaves with variables vs. methods.

---

## 🔑 Core Concept: Reference Type vs. Object Type

### 💡 Think of it like this:

* **Reference type** = What **Java *thinks* the object is** at **compile time**
* **Object type** = What the object **actually *is*** at **runtime**

---

### 🧠 Easy Analogy: **TV Remote and TV**

* **Reference** = Remote control (what buttons it *has*)
* **Object** = Actual TV (what it *can do*)
* You can point a basic remote (Parent) at a smart TV (Child), but you'll only have access to the buttons the remote knows about.

---

## 🔍 How Java Resolves Things

### 📦 Variables (Fields) → Use **Reference Type**

```java
class Parent {
    String name = "Parent";
}

class Child extends Parent {
    String name = "Child";
}

Parent p = new Child();
System.out.println(p.name);  // "Parent"
```

* Java checks the reference type (`Parent`) → so it uses `Parent`’s `name`, even though the object is a `Child`.
* This is **variable shadowing**, and it's resolved at **compile time**.

---

### ⚙️ Methods → Use **Object Type**

```java
class Parent {
    String getName() {
        return "Parent";
    }
}

class Child extends Parent {
    @Override
    String getName() {
        return "Child";
    }
}

Parent p = new Child();
System.out.println(p.getName());  // "Child"
```

* Java looks at the **actual object** at runtime (`Child`) → so it calls the `Child` version of the method.
* This is **method overriding**, and it’s resolved at **runtime**.

---

## 🧠 One-Line Rule to Remember:

> 🔁 **Methods go with the object (runtime)**,
> 📦 **Variables go with the reference (compile time)**

---

### 🧪 Bonus: What About Static?

* Static methods/variables are **class-level** → accessed by **class name or reference type**, not the object.

```java
class Parent {
    static String type = "Parent";
    static void greet() { System.out.println("Hi from Parent"); }
}

class Child extends Parent {
    static String type = "Child";
    static void greet() { System.out.println("Hi from Child"); }
}

Parent p = new Child();
System.out.println(p.type);  // "Parent"
p.greet();                   // "Hi from Parent"
```

---

## ✅ Final Cheatsheet

| Feature           | Resolved By    | When?        | Example                              |
| ----------------- | -------------- | ------------ | ------------------------------------ |
| Instance Variable | Reference Type | Compile Time | `p.name` uses `Parent`’s field       |
| Instance Method   | Object Type    | Runtime      | `p.getName()` calls `Child`’s method |
| Static Variable   | Reference Type | Compile Time | `p.type` uses `Parent`’s field       |
| Static Method     | Reference Type | Compile Time | `p.greet()` calls `Parent`’s method  |

---

Let me know if you'd like a visual or animation analogy!
