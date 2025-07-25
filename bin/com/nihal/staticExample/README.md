
https://dev.java/learn/classes-objects/nested-classes

## when inner class is static
`OuterClass.StaticNestedClass nestedObject = new OuterClass.StaticNestedClass();`


## when inner class is non static
`OuterClass outerClassObj = new OuterClass();`

`OuterClass.InnerClass innerObject = outerClassObj.new InnerClass();`