# Functional Reative Program
- using Signal Operations
- Signal is imutable
- Var of Signal for signals that can be changed
- Var provides an update "operation", which allows to redefine the value of a signal from the current time on

Signals of type Var look a bit like mutable variables, where:
~~~ scala 
sig()
~~~
is dereferencing, and 
~~~ scala
sig() = newValue
~~~
is update

But there's a crucial difference:
~~~ scala
a = 2
b = 2 * a
a = a + 1
// to update b, we need to assign again
b = 2 * a
b
~~~~ 
and using signal:
~~~ scala 
a() = 2
b() = 2 * a()
a() = 3
// b(), in this case, is automatically updated
b()
~~~
