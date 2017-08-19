`http://blog.csdn.net/tyler_download/article/details/52523237`

这节讲解是:引入C语言开发,

问题1:C是如何和汇编结合起来的呢?
 先通过命令`gcc -m32 -fno-asynchronous-unwind-tables -s -c -o write_vga.o write_vga.c` 
 把C语言编译成功,然后通过命令`./objconv -fnasm write_vga.asm write_vga.o`进行反汇编,

 把前面的`SECTION`注释掉,然后在kernel中通过`%include "write_vga.asm"` 引入文件,

 最后通过`nasm -o kernel.bat kernel.asm`可以得到`kernel.bat`文件,这个文件就是真正的内核代码

 ,其中这个文件也包括已经反编译成功的二进制文件。

 问题2:解析内核代码,

 ```
 mov   al, 0x13
 mov   ah, 0
 int   0x10

 ```
这三句代码的作用是把VGA的显卡打开。

```
LABEL_DESC_VRAM:    Descriptor        0,         0ffffffffh,            DA_DRW

```
加入4G可读写的描述符。为以后的开发C语言存储内存。

```
LABEL_DESC_STACK:   Descriptor        0,             TopOfStack,        DA_DRWA+DA_32

```
这里是栈描述符,

```
LABEL_STACK:
times 512  db 0
TopOfStack  equ  $ - LABEL_STACK
```
初始化`LABEL_STACK`, 初始化512个字节,这512个字节全部初始化为0,这512个字节全部用描述符描述,

这个描述符称为`LABEL_DESC_STACK`,C语言要通过一个堆栈来描述的, 把这个函数的参数琢渐压入堆栈,









