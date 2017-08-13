org 0x7c00; 基地址, origin的意思

jmp  entry  ; 3个字节
db   0x90   ; 内存位移的三个字节,申请一个字节的内容
DB   "OSKERNEL"  ;内存要分配9 个字节的内存
DW   512    ;申请2个字节的内存, 521正好占两个。
DB   1
DW   1
DB   2
DW   224
DW   2880
DB   0xf0
DW   9
DW   18
DW   2
DD   0
DD   2880
DB   0,0,0x29
DD   0xFFFFFFFF
DB   "MYFIRSTOS  "
DB   "FAT12   "
RESB  18


entry:
    mov  ax, 0 ;0赋值给ax寄存器, 寄存器放到cpu中,执行起来最快。
    mov  ss, ax ;寄存器 2个字节, 16位,如果不访问硬盘,可以把数据放到寄存器中
    mov  ds, ax
    mov  es, ax
    mov  si, msg

# 这里是个循环
putloop:
    mov  al, [si]  ;要读取si中的内存中的内容。第一个内容`0x0a`,al是前八位的的ax
    add  si, 1  ;si为指针
    cmp  al, 0
    je   fin
    mov  ah, 0x0e  ;ah 0x0e放到高八位的寄存器中。
    mov  bx, 15
    int  0x10    ;bios调用,相当于调用bios的api。
    jmp  putloop

fin:    ;机器的cup 终止状态,各种命令值都会停止
    HLT 
    jmp  fin

msg:
    DB    0x0a,  0x0a
    db    "hello, world zhangwenning"
    db    0x0a
    db    0



