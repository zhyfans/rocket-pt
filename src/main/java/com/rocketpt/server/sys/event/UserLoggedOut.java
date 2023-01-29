package com.rocketpt.server.sys.event;

import com.rocketpt.server.common.DomainEvent;
import com.rocketpt.server.sys.dto.UserinfoDTO;

/**
 * @author plexpt
 */
public record UserLoggedOut(UserinfoDTO dto) implements DomainEvent {
}