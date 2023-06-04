package com.ua.vinn.GammaLight.controllers;

import com.ua.vinn.GammaLight.models.Notification;
import com.ua.vinn.GammaLight.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllNotification(){
        return ResponseEntity.ok(notificationService.getAllNotification());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNotification(@PathVariable Long id) {

        notificationService.deleteNotification(id);
    }

    @DeleteMapping("/delete/all")
    public void deleteAllNotifications() {
        notificationService.deleteAllNotifications();
    }

}
