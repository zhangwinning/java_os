`http://blog.csdn.net/tyler_download/article/details/52242599`

在`实模式`中，寄存器中存储的段值;
在`保护模式`中,寄存器中存储的是引用,实际的段值存储到描述符中。


这里的逻辑是

整个内核的逻辑是先将字符串写入到5M起始的内存处,然后从5M内存处,将信息读取出来,显示到屏幕上。


```
66行:mov   si, msg		;把字符串地址赋值给si变量
67行: mov   ax, Selector5M    ;用 es 指向5M内存描述符
    mov   es, ax
    mov   edi, 0	//把ax赋值给es,同时设置偏移值为`edi`


write_msg_to_5M 这个函数的功能是向5M内存空间中写数据

ax这个寄存器是16位的,它的低8位ah表示颜色,高8位al是表示asII值

74行是把si中内存中的值放到al上,`edi`是偏移地址,而[es:edi]是从5M开始的位置加上偏移的

98行把al的值显示到显存中

```

