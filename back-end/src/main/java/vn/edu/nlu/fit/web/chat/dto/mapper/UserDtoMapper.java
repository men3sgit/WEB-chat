package vn.edu.nlu.fit.web.chat.dto.mapper;

import org.springframework.stereotype.Service;
import vn.edu.nlu.fit.web.chat.documents.User;
import vn.edu.nlu.fit.web.chat.dto.UserDto;
import vn.edu.nlu.fit.web.chat.utils.SpringDataUtil;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return SpringDataUtil.copyProperties(user, UserDto.class);
    }
}
