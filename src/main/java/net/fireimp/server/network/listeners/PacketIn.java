package net.fireimp.server.network.listeners;

import net.fireimp.server.network.player.NetPhase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PacketIn {
    NetPhase[] value() default NetPhase.ALL;
}
