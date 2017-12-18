package myproject;

import java.net.Socket;

public class DealThread implements Runnable {
    private Socket s;

    public DealThread(Socket s) {
        this.s = s;
    }

    public void run() {
        while (true) {
            byte[] buf = new byte[128];
            try {
                int len = s.getInputStream().read(buf);
                if (len < 0)
                    break;
                //Myproject.D("p", "包长度"+len);
                //System.out.println(len);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        int i = 0;
                        while ((len / 16) > i) {
                            int j = 16 * (i++);
                            //System.out.println("j"+j);
                            try {
                                byte[] subbuf = new byte[]
                                        {
                                                buf[j], buf[j + 1], buf[j + 2], buf[j + 3], buf[j + 4], buf[j + 5], buf[6 + j], buf[7 + j], buf[8 + j], buf[9 + j], buf[10 + j], buf[11 + j], buf[12 + j], buf[13 + j], buf[14 + j], buf[15 + j]
                                        };
                                new Thread(new DitalThread(subbuf)).start();
                            } catch (Exception e) {
                                Myproject.D("p", "接收数组长度错误");
                            }
                        }
                    }
                }).start();
            } catch (Exception e) {
                Myproject.D("d", "socket异常,已断开");
                break;
            }
        }

    }

}