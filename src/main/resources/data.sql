INSERT INTO users (username, password, enabled)
  values ('admin',
    '$2y$12$lN9Nl4PLAq2DlpxQTVEvWuyfjJ4RoBdmMw7rhAwuNWv2arJG837zC',
    1);

  INSERT INTO authorities (username, authority)
  values ('admin', 'ROLE_ADMIN');

  INSERT INTO authorities (username, authority)
  values ('admin', 'ROLE_USER');