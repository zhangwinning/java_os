
`http://blog.csdn.net/tyler_download/article/details/51970921`;

when the loader was putted into memory, it read the kernel to 0x8000, and then jump to kernel, giving the control to kernel code

当加载器被加载到内存中,通过`LOAD_ADDR  EQU  0X8000`,读到内核是在`0X8000`。
这时跳到内核`0X8000`的位置,然后把控制权交给`kernel`。
