package com.Enum;

/**
 * @author Vic Zhang
 * @date 2020/2/25 2:17 PM
 */
public enum Envent {

    PACKETENVENT("PacketEvent"),
    /**
     * Chat 测试
     */
    MESSAGEEVENT("MessageEvent");

    private String name;

    Envent(String name) { this.name = name; };
    
    @Override
    public String toString() {
        return this.name;
    }
}
