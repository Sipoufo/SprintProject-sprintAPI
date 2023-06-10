package com.sprint.sprintAPI.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.Metric;
import com.sprint.sprintAPI.entity.formats.MessageMetricFormat;
import com.sprint.sprintAPI.service.DeviceService;
import com.sprint.sprintAPI.service.MetricService;
import com.sprint.sprintAPI.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UsersService usersService;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private final DeviceService deviceService;
    @Autowired
    private final MetricService metricService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> usersService
                .fetchByUserEmail(username);
    }

    // She is responsible to fetch userDetail and also
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration conf) throws Exception {
        return conf.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(message.getPayload());
                try {
                    MessageMetricFormat messageMetricFormat = objectMapper.readValue((String) message.getPayload(), MessageMetricFormat.class);
                    Device deviceService1 = deviceService.fetchDeviceByRepresentationId(messageMetricFormat.device_representation);
                    System.out.println(deviceService1 != null);
                    if ( deviceService1 != null) {
                        Metric metric = Metric
                                .builder()
                                .device(deviceService1)
                                .metricValue(messageMetricFormat.value)
                                .unit(messageMetricFormat.unit)
                                .build();
                        metricService.saveMetric(metric);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        };
    }
}
