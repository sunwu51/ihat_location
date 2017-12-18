package top.microfrank.ihat_location.deal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.Socket;
import java.util.concurrent.ExecutorService;


public class DealThread implements Runnable {
    @Autowired
    private ExecutorService exec;
    private Socket s;
    private Logger logger=Logger.getLogger(DealThread.class);

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

                exec.execute(()-> {
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
                                exec.execute(new DitalThread(subbuf));
                            } catch (Exception e) {
                                logger.error("接收数组长度错误");
                            }
                        }
                    }
                    );
            } catch (Exception e) {
                logger.error("socket异常,已断开");
                break;
            }
        }

    }

}