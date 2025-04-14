package org.hae.yl;

import com.github.pagehelper.PageInfo;
import org.hae.yl.entity.Service_appointment;
import org.hae.yl.entity.Service_item;
import org.hae.yl.facade.SericeFacade;
import org.hae.yl.service.Service_appointmentService;
import org.hae.yl.service.Service_itemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SericeFacadeTest {

    @InjectMocks
    private SericeFacade sericeFacade;

    @Mock
    private Service_itemService service_itemService;

    @Mock
    private Service_appointmentService service_appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAvailableServices() {
        // 准备测试数据
        List<Service_item> mockServices = new ArrayList<>();
        Service_item service = new Service_item();
        service.setId(1);
        service.setName("测试服务");
        service.setDescription("服务描述");
        service.setPrice(new BigDecimal("100.00"));
        mockServices.add(service);

        // 设置mock行为
        when(service_itemService.SelectAll()).thenReturn(mockServices);

        // 执行测试
        PageInfo<Service_item> result = sericeFacade.getAllAvailableServices(1, 10);

        System.out.println(result.toString());
        // 验证结果
        assertNotNull(result);
        assertEquals(1, result.getList().size());
        assertEquals("测试服务", result.getList().get(0).getName());
    }

    @Test
    void testGetServiceDetail() {
        // 准备测试数据
        Service_item mockService = new Service_item();
        mockService.setId(1);
        mockService.setName("测试服务");
        mockService.setDescription("服务描述");
        mockService.setPrice(new BigDecimal("100.00"));

        // 设置mock行为
        when(service_itemService.SelectById(1)).thenReturn(mockService);

        // 执行测试
        Service_item result = sericeFacade.getServiceDetail(1);

        // 验证结果
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("测试服务", result.getName());
    }

    @Test
    void testSubmitAppointment() {
        // 准备测试数据
        Service_appointment appointment = new Service_appointment();
        appointment.setId(1);
        appointment.setUser_id(1);
        appointment.setService_id(1);
        appointment.setAppointment_time(LocalDateTime.now());
        appointment.setStatus(1);

        // 执行测试
        sericeFacade.submitAppointment(appointment);

        // 验证方法调用
        verify(service_appointmentService, times(1)).Insert(any(Service_appointment.class));
    }

    @Test
    void testCancelAppointment() {
        // 执行测试
        sericeFacade.cancelAppointment(1);

        // 验证方法调用
        verify(service_appointmentService, times(1)).DeleteById(1);
    }

    @Test
    void testGetAppointmentStatus() {
        // 准备测试数据
        Service_appointment mockAppointment = new Service_appointment();
        mockAppointment.setId(1);
        mockAppointment.setUser_id(1);
        mockAppointment.setService_id(1);
        mockAppointment.setAppointment_time(LocalDateTime.now());
        mockAppointment.setStatus(1);

        // 设置mock行为
        when(service_appointmentService.SelectById(1)).thenReturn(mockAppointment);

        // 执行测试
        Service_appointment result = sericeFacade.getAppointmentStatus(1);

        // 验证结果
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getStatus());
    }
} 