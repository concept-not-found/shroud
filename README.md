[![Shroud](https://github.com/concept-not-found/shroud/raw/gh-pages/images/shroud-logo.png)](http://github.com/concept-not-found/shroud)

## Shroud is a runtime object manipulation library for Java

## Motivation
Stop writing brain dead delegation code and remix objects with minimal code.

## Features

 - Shroud any object with a new interface, including final third-party classes!
 - Combine runtime objects into a single interface without having to implement the interface by hand
 - Object delegation/composition in a single line of code
 - Split monolith interfaces
 - AOP arounds at runtime
 - Adapt methods
 - Easy to use fluent builder

## Installation
Shroud is a [Maven](http://maven.apache.org/) project.
```xml
<dependency>
    <groupId>com.github.concept-not-found</groupId>
    <artifactId>shroud</artifactId>
    <version>3</version>
</dependency>
```

## Examples

### Implement interfaces at runtime
```java
	public interface Animal {
		String speak();
	}
	public class Dragon {
		public String speak() {
			return "rawr";
		}
	}
	...
	Animal animal = Shroud.shroud(new Dragon()).as(Animal.class);
	assertEquals("rawr", animal.speak());
```

### Bang two objects together
```java
	public interface Animal {
		String speak();
		String attack();
	}
	public class Dragon {
		public String attack() {
			return "flames";
		}
	}
	public class Kitten {
		public String speak() {
			return "meow";
		}
	}
	...
	Animal animal = Shroud.shroud(new Dragon(), new Kitten()).as(Animal.class);
	assertEquals("flames", animal.attack());
	assertEquals("meow", animal.speak());
```

### Say fork you to any object
```java
	public interface Getter {
		String getAnswer();
	}
	public interface Setter {
		void setAnswer(String answer);
	}
	public class Computer {
		private String answer = "unknown";
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(final String answer) {
			this.answer = answer;
		}
	}
	...
	Computer computer = new Computer();
	Getter getter = Shroud.shroud(computer).as(Getter.class);
	Setter setter = Shroud.shroud(computer).as(Setter.class);
	setter.setAnswer("42");
	assertEquals("42", getter.getAnswer());
```

### Back existing JavaBeans without the boilerplate
```java
	public class PropertiesOnly {
		private final boolean secure = true;
		private final String characterEncoding = "utf-8";
	}
	...
	PropertiesOnly properties = new PropertiesOnly();
	HttpServletRequest request = Shroud.shroud(properties).as(HttpServletRequest.class);
	assertTrue(request.isSecure());

	assertEquals("utf-8", request.getCharacterEncoding());
	request.setCharacterEncoding("yoda-speak");
	assertEquals("yoda-speak", ReflectionTestUtils.getField(properties, "characterEncoding"));
```

### Aspect-oriented programming around advice
```java
	public interface Animal {
		String attack();
	}
	public class Dragon {
		public String attack() {
			return "flames";
		}
	}
	public class SoundEffects {
		public Object attack(final Object target, final Method method, final Object[] parameters) throws Throwable {
			return "pew pew " + method.invoke(target, parameters);
		}
	}
	...
	Animal animal = Shroud.shroud(new Dragon()).advisedBy(new SoundEffects()).as(Animal.class);
	assertEquals("pew pew flames", animal.attack());
```

### Adapter pattern
```java
	public interface Duck {
		String quack();
	}
	public class Dragon {
		public String speak() {
			return "rawr";
		}
	}
	...
	Duck duck = Shroud.shroud(new Dragon()).map("quack", "speak").as(Duck.class);
	assertEquals("rawr", duck.quack());
```

##Copyright and License
<pre>
Copyright 2014 Ronald Chen

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
</pre>
