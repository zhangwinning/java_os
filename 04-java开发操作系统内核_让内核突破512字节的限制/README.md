
`http://blog.csdn.net/tyler_download/article/details/51970921`;

when the loader was putted into memory, it read the kernel to 0x8000, and then jump to kernel, giving the control to kernel code

当加载器被加载到内存中,通过`LOAD_ADDR  EQU  0X8000`,读到内核是在`0X8000`。
这时跳到内核`0X8000`的位置,然后把控制权交给`kernel`。


````

我们需要从内存中读取内核,要知道Linux代码要有几亿行,所以内核少说也有几百兆,几个G,我们要做的就是让内核

突破512字节。

结局方案,做内核加载器,通过加载器把内核数据加载到内存中,再跳到内核的起始位置,把控制权交个系统内核.

这个过程中,内核加载器就是起了一个中间桥梁的作用,

在`boot.asm`中的代码中,`readFloppy`函数中,加载器读到内核代码后,这时内核加载器把内核代码写到系统

内存的`0x8000`中内核代码开始的位置。

`entrt`的作用是把cpu中的寄存器置为0。

`jup`的作用是把控制区交个内核代码。