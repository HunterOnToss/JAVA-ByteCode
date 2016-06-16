**В данном проекте мы будем изучать байткод java виртуальной машины**

Что нам понадобиться:

javap
ASM bytecode outline

Коменда для декомпиляции:
javap -c -verbose [ClassName]


#============================================================================================
сегодня рассмотрим вторую секцию:

public class hunter.rae.ByteCode.Foo
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER

(52.0) версия каким компилятором был собран класс.
определяется k >= 2, в диапозоне от 45 до 44+k.0, где k версия явы

javac -source 1.6 Foo.java
ключ source проверяет только то что мы используете синтаксис явы 6, но код не запустится на яве 6 если он скомпилен на 8
javac -target 1.6 -source 1.6 Foo.java нужно использовать такую команду

flags таблицы тебе в помощь, мой юный падаван - https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html


#============================================================================================

Для класса Foo:

сегодня рассмотрим Constant pool:
 
 Constant pool:
    #1 = Methodref          #5.#14         // java/lang/Object."<init>":()V
    #2 = String             #15            // Hello world
    #3 = Fieldref           #4.#16         // hunter/rae/ByteCode/Foo.m:Ljava/lang/String;
    #4 = Class              #17            // hunter/rae/ByteCode/Foo
    #5 = Class              #18            // java/lang/Object
    #6 = Utf8               m
    #7 = Utf8               Ljava/lang/String;
    #8 = Utf8               <init>
    #9 = Utf8               ()V
   #10 = Utf8               Code
   #11 = Utf8               LineNumberTable
   #12 = Utf8               SourceFile
   #13 = Utf8               Foo.java
   #14 = NameAndType        #8:#9          // "<init>":()V
   #15 = Utf8               Hello world
   #16 = NameAndType        #6:#7          // m:Ljava/lang/String;
   #17 = Utf8               hunter/rae/ByteCode/Foo
   #18 = Utf8               java/lang/Object


#1 ссылка на метод >> #5 ссылка на класс >> #18 Object
#1 ссылка на тип и имя >> №14 ссылка на имя метода #8 и на тип #9()V - void (пустота холодна)
в #1 вызов конструктора object 

#2 Ссылка на строку 

#3 Ссылка на поле класса  >> #4 Имя класса 
#3 Ссылка на поле класса  >> #16 ссылка на имя переменной m #6 и на тип переменной #7 String
 
Класс Foo2:
  
Constant pool:
   #1 = Methodref          #7.#16         // java/lang/Object."<init>":()V
   #2 = Class              #17            // java/lang/String
   #3 = String             #18            // Hi!
   #4 = Methodref          #2.#19         // java/lang/String."<init>":(Ljava/lang/String;)V
   #5 = Fieldref           #6.#20         // hunter/rae/ByteCode/Foo2.m:Ljava/lang/String;
   #6 = Class              #21            // hunter/rae/ByteCode/Foo2
   #7 = Class              #22            // java/lang/Object
   #8 = Utf8               m
   #9 = Utf8               Ljava/lang/String;
  #10 = Utf8               <init>
  #11 = Utf8               ()V
  #12 = Utf8               Code
  #13 = Utf8               LineNumberTable
  #14 = Utf8               SourceFile
  #15 = Utf8               Foo2.java
  #16 = NameAndType        #10:#11        // "<init>":()V
  #17 = Utf8               java/lang/String
  #18 = Utf8               Hi!
  #19 = NameAndType        #10:#23        // "<init>":(Ljava/lang/String;)V
  #20 = NameAndType        #8:#9          // m:Ljava/lang/String;
  #21 = Utf8               hunter/rae/ByteCode/Foo2
  #22 = Utf8               java/lang/Object
  #23 = Utf8               (Ljava/lang/String;)V

Добавилась #4 ссылка на метод типа String метода init который используется заного в #10 из цепочки #1 >> #16 >> #10

из какого файла был скомпилирован byte code 
#14 = Utf8               SourceFile
#15 = Utf8               Foo2.java

max size of the CP ~65k