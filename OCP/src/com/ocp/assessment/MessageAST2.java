package com.ocp.assessment;

/**
 * Created by helangovan on 1/8/16.
 */
public abstract class MessageAST2 {
    public String recipient;
    //original dont compile as final and abstract dont go together
    //public abstract final  void sendMessage();
    public abstract  void sendMessage();
    public static void main(String []args){
        MessageAST2 m = new TextMessage();
        m.recipient = "1234567890";
        m.sendMessage();
    }

    static class TextMessage extends MessageAST2{
        public final void sendMessage(){
            System.out.print("Text Message to "+recipient);
        }
    }
}
