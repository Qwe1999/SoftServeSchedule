INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'teacher', 'schedule', 'public', 'teacher_pkey');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'teacher', 'schedule', 'public', 'teacher_firstname_lastname_key');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'teacher', 'schedule', 'public', 'schedule_teacherid_fkey');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'schedule', 'schedule', 'public', 'schedule_pkey');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'schedule', 'schedule', 'public', 'schedule_classid_numberlesson_daylesson_key');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'schedule', 'schedule', 'public', 'schedule_roomid_numberlesson_daylesson_key');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'schedule', 'schedule', 'public', 'schedule_teacherid_numberlesson_daylesson_key');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'subject', 'schedule', 'public', 'subject_pkey');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'subject', 'schedule', 'public', 'subject_name_key');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'subject', 'schedule', 'public', 'schedule_subjectid_fkey');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'class', 'schedule', 'public', 'class_pkey');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'class', 'schedule', 'public', 'class_number_key');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'class', 'schedule', 'public', 'schedule_classid_fkey');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'room', 'schedule', 'public', 'room_pkey');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'room', 'schedule', 'public', 'room_number_key');
INSERT INTO information_schema.constraint_table_usage (table_catalog, table_schema, table_name, constraint_catalog, constraint_schema, constraint_name) VALUES ('schedule', 'public', 'room', 'schedule', 'public', 'schedule_roomid_fkey');