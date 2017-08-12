import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class OperatingSystem {
    private int[] imgContent = new int[] {
        0xeb,0x4e,0x90,0x48,0x45,0x4c,0x4c,0x4f,0x49,0x50,0x4c,0x00,0x02,0x01,0x01,0x00,0x02,0xe0,
        0x00,0x40,0x0b,0xf0,0x09,0x00,0x12,0x00,0x02,0x00,0x00,0x00,0x00,0x00,0x40,0x0b,0x00,0x00,0x00,0x00,0x29,
        0xff,0xff,0xff,0xff,0x48,0x45,0x4c,0x4c,0x4f,0x2d,0x4f,0x53,0x20,0x20,0x20,0x46,0x41,0x54,0x31,0x32,
        0x20,0x20,0x20,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0xb8,0x00,0x00,0x8e,
        0xd0,0xbc,0x00,0x7c,0x8e,0xd8,0x8e,0xc0,0xbe,0x74,0x7c,0x8a,
        0x04,0x83,0xc6,0x01,0x3c,0x00,0x74,0x09,0xb4,0x0e,0xbb,0x0f,0x00,0xcd,0x10,0xeb,0xee,0xf4,0xeb,0xfd
    };

    private ArrayList<Integer> imgByteToWrite = new ArrayList<Integer>();

    public OperatingSystem(String s) {
        
        for(int i = 0; i < imgContent.length; i++) {
            imgByteToWrite.add(imgContent[i]);
        }

        imgByteToWrite.add(0X0a);
        imgByteToWrite.add(0X0a);

        for(int j = 0; j< s.length(); j++) {
            imgByteToWrite.add((int)s.charAt(j));
        }

        imgByteToWrite.add(0X0a);

        int len = 0x1fe;

        int curSize = imgByteToWrite.size();

        for(int k = 0; k < len - curSize; k++) {
            imgByteToWrite.add(0);
        }

        imgByteToWrite.add(0x55);
        imgByteToWrite.add(0xaa);
        imgByteToWrite.add(0xf0);
        imgByteToWrite.add(0xff);
        imgByteToWrite.add(0xff);

        len = 0x16800;

        curSize = imgByteToWrite.size();

        for(int m = 0; m<len - curSize; m++) {
            imgByteToWrite.add(0);
        }

    }

    public void makeFllopy() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("system.img"));

            for(int n = 0; n < imgByteToWrite.size(); n++) {
                out.writeByte(imgByteToWrite.get(n).byteValue());
            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        OperatingSystem op = new OperatingSystem("hello, this is my first line of my operating system code");
        op.makeFllopy();
    }


}

