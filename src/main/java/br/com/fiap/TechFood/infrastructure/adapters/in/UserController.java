package br.com.fiap.TechFood.infrastructure.adapters.in;

import br.com.fiap.TechFood.core.domain.User;
import br.com.fiap.TechFood.core.ports.PagePort;
import br.com.fiap.TechFood.core.service.UserService;
import br.com.fiap.TechFood.infrastructure.adapters.UserForm;
import br.com.fiap.TechFood.infrastructure.adapters.UserView;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserForm userForm) {
        User user = userService.create(userForm.toUser());

        URI uri = URI.create("/users/" + user.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/users")
    public ResponseEntity<PagePort<UserView>> listUser(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PagePort<UserView> usersDTO = userService.findAll(page, size).map(UserView::of);
        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserView> showUser(@PathVariable("id") Long id) {
        UserView userView = userService.findById(id).map(UserView::of).orElse(null);
        return ResponseEntity.ok(userView);
    }
}
