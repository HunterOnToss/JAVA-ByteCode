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

#============================================================================================
Сегодня посмотрим байт код метода noOp в Foo3

public static noOp()V
  L0
   LINENUMBER 10 L0
   RETURN
   MAXSTACK = 0
   MAXLOCALS = 0
   
frame => stack >> []
frame => massive local var >> []

Добавили return 2; и тип int

  public static noOp()I
   L0
    LINENUMBER 9 L0
    ICONST_2
    IRETURN
    MAXSTACK = 1
    MAXLOCALS = 0
    
frame => stack >> [2]
frame => massive local var >> []

IReturn снимает со стека число ICONST_2 и вернет его. После чего стек 
удалится

===
  public static returnConst(II)I
   L0
    LINENUMBER 13 L0
    ILOAD 0
    IRETURN
   L1
    LOCALVARIABLE a I L0 L1 0
    LOCALVARIABLE b I L0 L1 1
    MAXSTACK = 1
    MAXLOCALS = 2
   
    
ILOAD 0 возьмем переменную локальную из массива по индексу 0 и вернем
frame => stack >> []

LOCALVARIABLE a I L0 L1 0
LOCALVARIABLE b I L0 L1 1

MAXLOCALS =>>> frame => massive local var >> [a, b]

#============================================================================================
Разбираем метод public void test() {

L9
 LOCALVARIABLE this Lhunter/rae/ByteCode/Foo3; L0 L9 0 указатель на объект
 LOCALVARIABLE i1 D L1 L9 1
 LOCALVARIABLE i2 D L2 L9 3
 LOCALVARIABLE i3 I L3 L9 5
 LOCALVARIABLE i4 I L4 L9 6
 MAXSTACK = 4
 MAXLOCALS = 7

byte будет занимать скорее всего тоже 32 бита ! Нужно проверить!
double = 64 бита, int 32 бита

stack = []
local = [this, i1[double], i1, i2[double], i2, i3, i4]

   L0
    LINENUMBER 17 L0
    LDC 0.34
    DSTORE 1
   L1
    LINENUMBER 18 L1
    LDC 0.23
    DSTORE 3
   L2
    LINENUMBER 19 L2
    ICONST_0
    ISTORE 5
   L3
    LINENUMBER 20 L3
    ICONST_1
    ISTORE 6
    
Тут все понятно просто грузим локальные переменные значениями
LINENUMBER 17 L0 указывает на 17 строку в реальном коде
LDC - число и загружаем его на стек 
DSTORE - переносим значения со стека в массив локальных переменных
ICONST_0 часто используемые числа 


i1++;
i2 = i2 + i1;

  L4
    LINENUMBER 21 L4
    DLOAD 1
    DCONST_1
    DADD
    DSTORE 1
   L5
    LINENUMBER 22 L5
    DLOAD 3
    DLOAD 1
    DADD
    DSTORE 3
    
DLOAD загружает из ячейки локальных переменных значение 
DCONST_1 добавляет константу 1.00 на стек 
DADD сложить два добла со стека 
DSTORE поместить результат
 
L6
 LINENUMBER 23 L6
 IINC 5 1

IINC к 5 локальной переменной добавь 1

что быстрее 

i1 = i1 + i1
i2 = i2 + i2
i3 = i3 + i3
i4 = i4 + i4

или же 

i1 = i1 + i1
i1 = i1 + i1

#============================================================================================
Работаем с if(){}else{}

public static mod(I)I
   L0
    LINENUMBER 28 L0
    ILOAD 0
    IFLT L1
   L2
    LINENUMBER 29 L2
    ILOAD 0
    IRETURN
   L1
    LINENUMBER 31 L1
   FRAME SAME
    ILOAD 0
    INEG
    IRETURN
   L3
    LOCALVARIABLE val I L0 L3 0
    MAXSTACK = 1
    MAXLOCALS = 1
    
IFLT - if lower than
INEG - переводит число в отрицание

public static mod2(I)I
   L0
    LINENUMBER 37 L0
    ILOAD 0
    IFLT L1
   L2
    LINENUMBER 38 L2
    ILOAD 0
    ISTORE 1
   L3
    GOTO L4
   L1
    LINENUMBER 40 L1
   FRAME SAME
    ILOAD 0
    INEG
    ISTORE 1
   L4
    LINENUMBER 43 L4
   FRAME APPEND [I]
    ILOAD 1
    IRETURN
   L5
    LOCALVARIABLE result I L3 L1 1
    LOCALVARIABLE val I L0 L5 0
    LOCALVARIABLE result I L4 L5 1
    MAXSTACK = 1
    MAXLOCALS = 2

GOTO старый добрый. 

public static max(II)I
   L0
    LINENUMBER 47 L0
    ILOAD 0
    ILOAD 1
    IF_ICMPLT L1
   L2
    LINENUMBER 48 L2
    ILOAD 0
    IRETURN
   L1
    LINENUMBER 50 L1
   FRAME SAME
    ILOAD 1
    IRETURN
   L3
    LOCALVARIABLE left I L0 L3 0
    LOCALVARIABLE right I L0 L3 1
    MAXSTACK = 2
    MAXLOCALS = 2


IF_ICMPLT succeeds if and only if value1 < value2

  public static max(FF)F
   L0
    LINENUMBER 63 L0
    FLOAD 0
    FLOAD 1
    FCMPL
    IFLT L1
   L2
    LINENUMBER 64 L2
    FLOAD 0
    FRETURN
   L1
    LINENUMBER 66 L1
   FRAME SAME
    FLOAD 1
    FRETURN
   L3
    LOCALVARIABLE left F L0 L3 0
    LOCALVARIABLE right F L0 L3 1
    MAXSTACK = 2
    MAXLOCALS = 2
}

FCMPL FCMPG DCMPL DCMPG отличаются только тем что будут разные проверки 
c NaN