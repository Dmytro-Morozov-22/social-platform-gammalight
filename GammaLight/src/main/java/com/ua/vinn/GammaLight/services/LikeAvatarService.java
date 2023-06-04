package com.ua.vinn.GammaLight.services;

import com.ua.vinn.GammaLight.models.Avatar;
import com.ua.vinn.GammaLight.models.LikeAvatar;
import com.ua.vinn.GammaLight.models.User;
import com.ua.vinn.GammaLight.repositories.LikeAvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeAvatarService {

    private final LikeAvatarRepository likeAvatarRepository;
    private final UserService userService;
    private final AvatarService avatarService;

    @Autowired
    public LikeAvatarService(LikeAvatarRepository likeAvatarRepository, UserService userService, AvatarService avatarService) {
        this.likeAvatarRepository = likeAvatarRepository;
        this.userService = userService;
        this.avatarService = avatarService;
    }

    public long getNumberOfAvatarLikes() {
        return likeAvatarRepository.count();
    }

    public void putOrDeleteAvatarLike(Long avatarId) {
        User user = userService.getUserFromContext();
        likeAvatarRepository.findByUserId(user.getId())
                .ifPresentOrElse(x -> deleteAvatarLike(x.getId()), () -> putAvatarLike(avatarId, user));
    }

    private void putAvatarLike(Long avatarId, User user) {
        Avatar avatar = avatarService.getAvatarById(avatarId);
        likeAvatarRepository.save(new LikeAvatar(user, avatar));
    }

    private void deleteAvatarLike(Long likeAvatarId) {
        likeAvatarRepository.deleteById(likeAvatarId);
    }
}
