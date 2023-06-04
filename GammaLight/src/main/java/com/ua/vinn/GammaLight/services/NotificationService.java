package com.ua.vinn.GammaLight.services;

import com.ua.vinn.GammaLight.dto.response.NotificationResponseDto;
import com.ua.vinn.GammaLight.models.Notification;
import com.ua.vinn.GammaLight.models.User;
import com.ua.vinn.GammaLight.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserService userService;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserService userService) {
        this.notificationRepository = notificationRepository;
        this.userService = userService;
    }

    public void sendNotification(Long followedUserId, User user){
       notificationRepository.save(new Notification(followedUserId, user));
    }

    public List<NotificationResponseDto> getAllNotification(){
        Long id = userService.getUserFromContext().getId();
        List<Notification> allNotification = notificationRepository.findAllByFollowedUserId(id);
        return createNotificationResponse(allNotification);
    }

    private List<NotificationResponseDto> createNotificationResponse(List<Notification> userNotifications){
        List<NotificationResponseDto> listNotifications = new ArrayList<>();
        for(Notification each : userNotifications) {
            String firstName = each.getUser().getFirstName();
            String avatarLink = each.getUser().getAvatar().get(0).getAvatarLink();
            listNotifications.add(new NotificationResponseDto(avatarLink, firstName));
        }
        return listNotifications;
    }

    // TODO - user can only delete their own notification
    @Transactional
    public void deleteNotification(Long userId){
        notificationRepository.deleteByUserId(userId);
    }

    // TODO - user can only delete their own notification
    public void deleteAllNotifications(){
        notificationRepository.deleteAll();
    }
}
