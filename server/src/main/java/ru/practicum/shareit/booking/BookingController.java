package ru.practicum.shareit.booking;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    private final String header = "X-Sharer-User-Id";
    private final BookingService bookingService;

    @PostMapping
    public BookingDto create(@RequestHeader(header) Long userId,
                             @Valid @RequestBody BookingRequestDto bookingRequestDto) {
        return bookingService.create(userId, bookingRequestDto);
    }

    @GetMapping
    public Collection<BookingDto> findAll(
            @RequestHeader(header) Long userId,
            @RequestParam(defaultValue = "ALL", required = false) String state) {
        return bookingService.findAllByBookerAndStatus(userId, state);
    }

    @GetMapping("/owner")
    public Collection<BookingDto> findAllByOwnerAndStatus(
            @RequestHeader(header) Long userId,
            @RequestParam(defaultValue = "ALL", required = false) String state) {
        return bookingService.findAllByOwnerAndStatus(userId, state);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto setApproved(@RequestHeader(header) Long userId,
                                  @PathVariable Long bookingId,
                                  @RequestParam Boolean approved) {
        return bookingService.setApproved(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDto findById(@RequestHeader(header) Long userId,
                               @PathVariable Long bookingId) {
        return bookingService.findById(bookingId, userId);
    }
}