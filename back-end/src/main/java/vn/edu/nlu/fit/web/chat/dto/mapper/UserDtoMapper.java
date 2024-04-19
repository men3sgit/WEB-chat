package vn.edu.nlu.fit.web.chat.dto.mapper;

import org.springframework.stereotype.Service;
import vn.edu.nlu.fit.web.chat.model.User;
import vn.edu.nlu.fit.web.chat.utils.SpringDataUtil;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, vn.edu.nlu.fit.web.chat.dto.UserDto> {
    @Override
    public vn.edu.nlu.fit.web.chat.dto.UserDto apply(User user) {
        return SpringDataUtil.copyProperties(user, vn.edu.nlu.fit.web.chat.dto.UserDto.class);
    }
}
