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