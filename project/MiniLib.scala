package build

object MiniLib {
  val Whitelist = {
    val inJavaLang = List(
        "Object",
        "ObjectClone",
        "Class",
        "System",
        "System$IDHashCode",

        "CharSequence",
        "Cloneable",
        "Comparable",
        "Number",

        "Void",
        "Boolean",
        "Character",
        "Byte",
        "Short",
        "Integer",
        "Long",
        "Float",
        "Double",
        "String",

        "FloatingPointBits",
        "FloatingPointBits$EncodeIEEE754Result",

        "Throwable",
        "StackTrace",
        "Error",
        "VirtualMachineError",
        "Exception",
        "RuntimeException",
        "ArithmeticException",
        "ArrayIndexOutOfBoundsException",
        "ArrayStoreException",
        "ClassCastException",
        "CloneNotSupportedException",
        "IndexOutOfBoundsException",
        "NullPointerException",
        "StringIndexOutOfBoundsException"
    ).map("java/lang/" + _)

    val inJavaIO = List(
        "Serializable"
    ).map("java/io/" + _)

    val allBaseNames = inJavaLang ::: inJavaIO

    allBaseNames.flatMap(name => List(name + ".sjsir", name + "$.sjsir")).toSet
  }
}
