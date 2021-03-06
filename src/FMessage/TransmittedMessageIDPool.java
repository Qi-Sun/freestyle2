/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import java.util.ArrayList;
import java.util.Random;

/**
 * 消息ID池
 * @author sq
 */
public class TransmittedMessageIDPool {
    
    private String Owner;
    private ArrayList<String> IdPool;
    public static int MinNumber=100000;
    public static int MaxNumber=999999;
    public Random NumberCreater;

    /**
     *
     * @param owner 拥有者
     */
    public TransmittedMessageIDPool(String owner) {
        this.NumberCreater = new Random();
        this.Owner=owner;
        this.IdPool = new ArrayList<String>();
    }
    
    /**
     * 获得一个随机的消息ID
     * @param Sender 发送者
     * @return
     */
    public String getOneRandomID(String Sender)
    {
        int randomnumber = NumberCreater.nextInt(MaxNumber)%(MaxNumber-MinNumber+1) + MinNumber;
        while(IdPool.contains(Integer.toString(randomnumber)) == true)
        {
            randomnumber = NumberCreater.nextInt(MaxNumber)%(MaxNumber-MinNumber+1) + MinNumber;
        }
        IdPool.add(Integer.toString(randomnumber));
        return Sender+"_"+Integer.toString(randomnumber);
    }
    
    /**
     * 判断ID是否在id池中
     * @param msgID
     * @return
     */
    public boolean isMsgIDinPool(String msgID)
    {
        String sender=msgID.split("_")[0];
        String id=msgID.split("_")[1];
        if (!sender.equals(Owner )|| !IdPool.contains(id)) {
            return false;
        }
        else return true;
    }
    
    /**
     * 将ID从id池中移除
     * @param msgID
     * @return
     */
    public boolean removeMsgIDfromPool(String msgID)
    {
        if (isMsgIDinPool(msgID) == true) {
            IdPool.remove(msgID.split("_")[1]);
            return true;
        }
        else return false;
    }
    
}
