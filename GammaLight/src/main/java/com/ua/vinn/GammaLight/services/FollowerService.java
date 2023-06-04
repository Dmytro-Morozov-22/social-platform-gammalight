package com.ua.vinn.GammaLight.services;

import com.ua.vinn.GammaLight.dto.response.NotificationResponseDto;
import com.ua.vinn.GammaLight.models.Follower;
import com.ua.vinn.GammaLight.models.Notification;
import com.ua.vinn.GammaLight.models.User;
import com.ua.vinn.GammaLight.repositories.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowerService {

   private final FollowerRepository followerRepository;
   private final UserService userService;
   private final NotificationService notificationService;

    @Autowired
    public FollowerService(FollowerRepository followerRepository, UserService userService, NotificationService notificationService) {
        this.followerRepository = followerRepository;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public void follow(Long followedUserId) {
        User user = userService.getUserFromContext();
        followerRepository.save(new Follower(followedUserId, user));
        notificationService.sendNotification(followedUserId, user);
    }


    public List<NotificationResponseDto> getAllFollowers(){
        Long id = userService.getUserFromContext().getId();
        List<Follower> allFollowers = followerRepository.findAllByFollowedUserId(id);
        return createNotificationResponse(allFollowers);
    }

    private List<NotificationResponseDto> createNotificationResponse(List<Follower> userFollowers){
        List<NotificationResponseDto> listNotifications = new ArrayList<>();
        for(Follower each : userFollowers) {
            String firstName = each.getUser().getFirstName();
            String avatarLink = each.getUser().getAvatar().get(0).getAvatarLink();
            listNotifications.add(new NotificationResponseDto(avatarLink, firstName));
        }
        return listNotifications;
    }



    @Transactional
    public void unfollow(Long followedUserId){
        followerRepository.deleteByFollowedUserId(followedUserId);
        notificationService.deleteNotification(followedUserId);
    }

}
