package io.github.qqbot.spring.model;

import java.util.List;

public record RoleMembersResponse(List<Member> data, String next) { }
