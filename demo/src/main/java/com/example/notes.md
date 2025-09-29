# MVC Model
M - Model (data struuctures)
V - View (Graphical User Interface)
C - Controller (Respond to keypresses, buttons, etc.)

# Map
A map is a data structure that contains key- value pairs. You can think of it like an array where the indices are values that we choose.

We declare maps:
```java
HashMap<Key, Value> map = new HashMap();
```

Accessing elements
```java
map.get(key);
```

Does an entry exist
```java
map.containsKey(key);
```

Add things to the map
```java
map.put(key, value);
```

Get all the keys, so that I can loop through them
```java
hashSet<Key> keys = map.keySet();

for(Key current : keys) {...}
```

What is a set?
* A data structure where duplicates are NOT allowed

In KnightsTour, what does our map look like?
```java
HashMap<Location, ArrayList<Location>> exhausetedList = new HashMap();
```