import com.epam.jmp.service.impl.ServiceImpl;

module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    requires org.apache.commons.lang3;

    provides com.epam.jmp.service.Service with ServiceImpl;
    exports com.epam.jmp.service.impl;

}
