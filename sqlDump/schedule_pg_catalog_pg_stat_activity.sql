INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (null, null, 8276, null, null, '', null, null, null, '2019-11-20 06:39:12.701865', null, null, null, 'Activity', 'AutoVacuumMain', null, null, null, '', 'autovacuum launcher');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (null, null, 8292, 10, 'postgres', '', null, null, null, '2019-11-20 06:39:12.702494', null, null, null, 'Activity', 'LogicalLauncherMain', null, null, null, '', 'logical replication launcher');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (13318, 'postgres', 16188, 10, 'postgres', 'pgAdmin 4 - DB:postgres', '::1', null, 52723, '2019-11-21 08:28:16.819386', null, '2019-11-21 09:38:18.865839', '2019-11-21 09:38:18.883870', 'Client', 'ClientRead', 'idle', null, null, '/*pga4dash*/
SELECT ''session_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT count(*) FROM pg_stat_activity) AS "Total",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''active'')  AS "Active",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''idle'')  AS "Idle"
) t
UNION ALL
SELECT ''tps_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT sum(xact_commit) + sum(xact_rollback) FROM pg_stat_database) AS "Transactions",
   (SELECT sum(xact_commit) FROM pg_stat_database) AS "Commits",
   (SELECT sum(xact_rollback) FROM pg_stat_database) AS "Rollbacks"
) t
UNION ALL
SELECT ''ti_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT sum(tup_inserted) FROM pg_stat_database) AS "Inserts",
   (SELECT sum(tup_updated) FROM pg_stat_database) AS "Updates",
   (SELECT sum(tup_deleted) FROM pg_stat_database) AS "Deletes"
) t
UNION ALL
SELECT ''to_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT sum(tup_fetched) FROM ', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 6756, 10, 'postgres', 'pgAdmin 4 - DB:schedule', '::1', null, 52724, '2019-11-21 08:28:17.442678', null, '2019-11-21 09:35:38.804536', '2019-11-21 09:35:38.817680', 'Client', 'ClientRead', 'idle', null, null, '
SELECT cl.relkind, COALESCE(cin.nspname, cln.nspname) as nspname,
    COALESCE(ci.relname, cl.relname) as relname, cl.relname as indname
FROM pg_class cl
JOIN pg_namespace cln ON cl.relnamespace=cln.oid
LEFT OUTER JOIN pg_index ind ON ind.indexrelid=cl.oid
LEFT OUTER JOIN pg_class ci ON ind.indrelid=ci.oid
LEFT OUTER JOIN pg_namespace cin ON ci.relnamespace=cin.oid
WHERE cl.oid IN (SELECT objid FROM pg_shdepend WHERE refobjid=10::oid) AND cl.oid > 13317::oid
UNION ALL SELECT ''n'', null, nspname, null
    FROM pg_namespace nsp
    WHERE nsp.oid IN (SELECT objid FROM pg_shdepend WHERE refobjid=10::oid) AND nsp.oid > 13317::oid
UNION ALL SELECT CASE WHEN typtype=''d'' THEN ''d'' ELSE ''y'' END, null, typname, null
    FROM pg_type ty
    WHERE ty.oid IN (SELECT objid FROM pg_shdepend WHERE refobjid=10::oid) AND ty.oid > 13317::oid
UNION ALL SELECT ''C'', null, conname, null
    FROM pg_conversion co
    WHERE co.oid IN (SELECT objid FROM pg_shdepend WHERE refobjid=10::oid) AND co.oid > 13317::oid
UNION ALL SELECT CASE ', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (26734, 'scheduleTest', 9924, 10, 'postgres', 'pgAdmin 4 - DB:scheduleTest', '::1', null, 52725, '2019-11-21 08:28:18.022243', null, '2019-11-21 09:35:38.819630', '2019-11-21 09:35:38.827026', 'Client', 'ClientRead', 'idle', null, null, '
SELECT cl.relkind, COALESCE(cin.nspname, cln.nspname) as nspname,
    COALESCE(ci.relname, cl.relname) as relname, cl.relname as indname
FROM pg_class cl
JOIN pg_namespace cln ON cl.relnamespace=cln.oid
LEFT OUTER JOIN pg_index ind ON ind.indexrelid=cl.oid
LEFT OUTER JOIN pg_class ci ON ind.indrelid=ci.oid
LEFT OUTER JOIN pg_namespace cin ON ci.relnamespace=cin.oid
WHERE cl.oid IN (SELECT objid FROM pg_shdepend WHERE refobjid=10::oid) AND cl.oid > 13317::oid
UNION ALL SELECT ''n'', null, nspname, null
    FROM pg_namespace nsp
    WHERE nsp.oid IN (SELECT objid FROM pg_shdepend WHERE refobjid=10::oid) AND nsp.oid > 13317::oid
UNION ALL SELECT CASE WHEN typtype=''d'' THEN ''d'' ELSE ''y'' END, null, typname, null
    FROM pg_type ty
    WHERE ty.oid IN (SELECT objid FROM pg_shdepend WHERE refobjid=10::oid) AND ty.oid > 13317::oid
UNION ALL SELECT ''C'', null, conname, null
    FROM pg_conversion co
    WHERE co.oid IN (SELECT objid FROM pg_shdepend WHERE refobjid=10::oid) AND co.oid > 13317::oid
UNION ALL SELECT CASE ', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (26734, 'scheduleTest', 15428, 10, 'postgres', 'pgAdmin 4 - CONN:350573', '::1', null, 52796, '2019-11-21 08:28:26.343342', null, '2019-11-21 08:29:13.662879', '2019-11-21 08:29:13.664266', 'Client', 'ClientRead', 'idle', null, null, 'COMMIT;', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (26734, 'scheduleTest', 8812, 10, 'postgres', 'pgAdmin 4 - CONN:7117809', '::1', null, 53111, '2019-11-21 08:31:25.645312', null, '2019-11-21 08:31:26.202599', '2019-11-21 08:31:26.213909', 'Client', 'ClientRead', 'idle', null, null, 'SELECT att.attname as name, att.attnum as OID, format_type(ty.oid,NULL) AS datatype,
att.attnotnull as not_null, att.atthasdef as has_default_val
FROM pg_attribute att
  JOIN pg_type ty ON ty.oid=atttypid
  JOIN pg_namespace tn ON tn.oid=ty.typnamespace
  JOIN pg_class cl ON cl.oid=att.attrelid
  JOIN pg_namespace na ON na.oid=cl.relnamespace
  LEFT OUTER JOIN pg_type et ON et.oid=ty.typelem
  LEFT OUTER JOIN pg_attrdef def ON adrelid=att.attrelid AND adnum=att.attnum
  LEFT OUTER JOIN (pg_depend JOIN pg_class cs ON classid=''pg_class''::regclass AND objid=cs.oid AND cs.relkind=''S'') ON refobjid=att.attrelid AND refobjsubid=att.attnum
  LEFT OUTER JOIN pg_namespace ns ON ns.oid=cs.relnamespace
  LEFT OUTER JOIN pg_index pi ON pi.indrelid=att.attrelid AND indisprimary
WHERE
    att.attrelid = 26737::oid
    AND att.attnum > 0
    AND att.attisdropped IS FALSE
ORDER BY att.attnum', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (26734, 'scheduleTest', 12500, 10, 'postgres', 'pgAdmin 4 - CONN:8607874', '::1', null, 53649, '2019-11-21 08:35:38.865006', null, '2019-11-21 08:35:39.443251', '2019-11-21 08:35:39.456998', 'Client', 'ClientRead', 'idle', null, null, 'SELECT att.attname as name, att.attnum as OID, format_type(ty.oid,NULL) AS datatype,
att.attnotnull as not_null, att.atthasdef as has_default_val
FROM pg_attribute att
  JOIN pg_type ty ON ty.oid=atttypid
  JOIN pg_namespace tn ON tn.oid=ty.typnamespace
  JOIN pg_class cl ON cl.oid=att.attrelid
  JOIN pg_namespace na ON na.oid=cl.relnamespace
  LEFT OUTER JOIN pg_type et ON et.oid=ty.typelem
  LEFT OUTER JOIN pg_attrdef def ON adrelid=att.attrelid AND adnum=att.attnum
  LEFT OUTER JOIN (pg_depend JOIN pg_class cs ON classid=''pg_class''::regclass AND objid=cs.oid AND cs.relkind=''S'') ON refobjid=att.attrelid AND refobjsubid=att.attnum
  LEFT OUTER JOIN pg_namespace ns ON ns.oid=cs.relnamespace
  LEFT OUTER JOIN pg_index pi ON pi.indrelid=att.attrelid AND indisprimary
WHERE
    att.attrelid = 26777::oid
    AND att.attnum > 0
    AND att.attisdropped IS FALSE
ORDER BY att.attnum', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (26734, 'scheduleTest', 10216, 10, 'postgres', 'pgAdmin 4 - CONN:3556187', '::1', null, 54449, '2019-11-21 08:41:36.617906', null, '2019-11-21 08:41:37.264728', '2019-11-21 08:41:37.282689', 'Client', 'ClientRead', 'idle', null, null, 'SELECT att.attname as name, att.attnum as OID, format_type(ty.oid,NULL) AS datatype,
att.attnotnull as not_null, att.atthasdef as has_default_val
FROM pg_attribute att
  JOIN pg_type ty ON ty.oid=atttypid
  JOIN pg_namespace tn ON tn.oid=ty.typnamespace
  JOIN pg_class cl ON cl.oid=att.attrelid
  JOIN pg_namespace na ON na.oid=cl.relnamespace
  LEFT OUTER JOIN pg_type et ON et.oid=ty.typelem
  LEFT OUTER JOIN pg_attrdef def ON adrelid=att.attrelid AND adnum=att.attnum
  LEFT OUTER JOIN (pg_depend JOIN pg_class cs ON classid=''pg_class''::regclass AND objid=cs.oid AND cs.relkind=''S'') ON refobjid=att.attrelid AND refobjsubid=att.attnum
  LEFT OUTER JOIN pg_namespace ns ON ns.oid=cs.relnamespace
  LEFT OUTER JOIN pg_index pi ON pi.indrelid=att.attrelid AND indisprimary
WHERE
    att.attrelid = 26767::oid
    AND att.attnum > 0
    AND att.attisdropped IS FALSE
ORDER BY att.attnum', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (26734, 'scheduleTest', 9704, 10, 'postgres', 'pgAdmin 4 - CONN:4524494', '::1', null, 56036, '2019-11-21 08:54:00.185365', null, '2019-11-21 09:08:27.362148', '2019-11-21 09:08:27.363655', 'Client', 'ClientRead', 'idle', null, null, 'COMMIT;', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (26734, 'scheduleTest', 16780, 10, 'postgres', 'pgAdmin 4 - CONN:3945152', '::1', null, 58314, '2019-11-21 09:10:26.965342', null, '2019-11-21 09:10:27.603806', '2019-11-21 09:10:27.618145', 'Client', 'ClientRead', 'idle', null, null, 'SELECT att.attname as name, att.attnum as OID, format_type(ty.oid,NULL) AS datatype,
att.attnotnull as not_null, att.atthasdef as has_default_val
FROM pg_attribute att
  JOIN pg_type ty ON ty.oid=atttypid
  JOIN pg_namespace tn ON tn.oid=ty.typnamespace
  JOIN pg_class cl ON cl.oid=att.attrelid
  JOIN pg_namespace na ON na.oid=cl.relnamespace
  LEFT OUTER JOIN pg_type et ON et.oid=ty.typelem
  LEFT OUTER JOIN pg_attrdef def ON adrelid=att.attrelid AND adnum=att.attnum
  LEFT OUTER JOIN (pg_depend JOIN pg_class cs ON classid=''pg_class''::regclass AND objid=cs.oid AND cs.relkind=''S'') ON refobjid=att.attrelid AND refobjsubid=att.attnum
  LEFT OUTER JOIN pg_namespace ns ON ns.oid=cs.relnamespace
  LEFT OUTER JOIN pg_index pi ON pi.indrelid=att.attrelid AND indisprimary
WHERE
    att.attrelid = 26767::oid
    AND att.attnum > 0
    AND att.attisdropped IS FALSE
ORDER BY att.attnum', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 13740, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59496, '2019-11-21 09:24:44.423437', null, '2019-11-21 09:24:44.448140', '2019-11-21 09:24:44.448170', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 15172, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59497, '2019-11-21 09:24:44.541530', null, '2019-11-21 09:24:44.568137', '2019-11-21 09:24:44.568159', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 8180, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59499, '2019-11-21 09:24:44.677348', null, '2019-11-21 09:24:44.702228', '2019-11-21 09:24:44.702254', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 7496, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59502, '2019-11-21 09:24:44.802485', null, '2019-11-21 09:24:44.824574', '2019-11-21 09:24:44.824595', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 14100, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59796, '2019-11-21 09:27:41.756882', null, '2019-11-21 09:27:42.267768', '2019-11-21 09:27:42.267868', 'Client', 'ClientRead', 'idle', null, null, 'SELECT * FROM CLASS ', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 13728, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59797, '2019-11-21 09:27:41.875160', null, '2019-11-21 09:27:41.899779', '2019-11-21 09:27:41.899814', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 256, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59798, '2019-11-21 09:27:41.996115', null, '2019-11-21 09:27:42.020292', '2019-11-21 09:27:42.020317', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 15436, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59799, '2019-11-21 09:27:42.111104', null, '2019-11-21 09:27:42.135602', '2019-11-21 09:27:42.135630', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 13796, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59800, '2019-11-21 09:27:42.232830', null, '2019-11-21 09:27:42.262295', '2019-11-21 09:27:42.262776', 'Client', 'ClientRead', 'idle', null, null, 'SELECT  schedule.id as scheduleId, schedule.dayLesson as DayLesson,  schedule.numberLesson as NumberLesson,  class.id as classId, class.number as classNumber,  room.id as roomId, room.number as roomNumber,  subject.id as subjectId, subject.name as subjectName,  teacher.id as teacherId, teacher.firstName as FirstName,  teacher.lastName as LastName  from schedule  JOIN class ON class.id = schedule.classid  JOIN room ON room.id = schedule.roomid  JOIN subject ON subject.id = schedule.subjectid  JOIN teacher ON teacher.id = schedule.teacherid ', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 9044, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 60892, '2019-11-21 09:38:19.680098', '2019-11-21 09:38:19.728145', '2019-11-21 09:38:19.729879', '2019-11-21 09:38:19.729880', null, null, 'active', null, 2415, 'SELECT t.* FROM pg_catalog.pg_stat_activity t', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 8332, 10, 'postgres', 'pgAdmin 4 - CONN:369564', '::1', null, 59148, '2019-11-21 09:22:07.336966', null, '2019-11-21 09:27:07.832717', '2019-11-21 09:27:07.834464', 'Client', 'ClientRead', 'idle', null, null, 'COMMIT;', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 10764, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59420, '2019-11-21 09:24:06.569263', null, '2019-11-21 09:24:06.595465', '2019-11-21 09:24:06.595495', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 10656, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59421, '2019-11-21 09:24:06.695525', null, '2019-11-21 09:24:06.718420', '2019-11-21 09:24:06.718451', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 16972, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59422, '2019-11-21 09:24:06.815787', null, '2019-11-21 09:24:06.836926', '2019-11-21 09:24:06.836943', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 8228, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59423, '2019-11-21 09:24:06.928769', null, '2019-11-21 09:24:06.951537', '2019-11-21 09:24:06.951563', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 5612, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59787, '2019-11-21 09:27:36.942604', null, '2019-11-21 09:27:36.968469', '2019-11-21 09:27:36.968508', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 14508, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59788, '2019-11-21 09:27:37.066537', null, '2019-11-21 09:27:37.088472', '2019-11-21 09:27:37.088488', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 17064, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59789, '2019-11-21 09:27:37.189544', null, '2019-11-21 09:27:37.214378', '2019-11-21 09:27:37.214406', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 16516, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59790, '2019-11-21 09:27:37.319570', null, '2019-11-21 09:27:37.342410', '2019-11-21 09:27:37.342436', 'Client', 'ClientRead', 'idle', null, null, 'SET application_name = ''PostgreSQL JDBC Driver''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (16393, 'schedule', 14208, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 59791, '2019-11-21 09:27:37.448117', null, '2019-11-21 09:27:37.482023', '2019-11-21 09:27:37.482296', 'Client', 'ClientRead', 'idle', null, null, 'SELECT  schedule.id as scheduleId, schedule.dayLesson as DayLesson,  schedule.numberLesson as NumberLesson,  class.id as classId, class.number as classNumber,  room.id as roomId, room.number as roomNumber,  subject.id as subjectId, subject.name as subjectName,  teacher.id as teacherId, teacher.firstName as FirstName,  teacher.lastName as LastName from schedule  JOIN class ON class.id = schedule.classid JOIN room ON room.id = schedule.roomid JOIN subject ON subject.id = schedule.subjectid JOIN teacher ON teacher.id = schedule.teacherid WHERE LOWER(subject.name) = LOWER($1)', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (null, null, 104, null, null, '', null, null, null, '2019-11-20 06:39:12.698085', null, null, null, 'Activity', 'BgWriterMain', null, null, null, '', 'background writer');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (null, null, 4212, null, null, '', null, null, null, '2019-11-20 06:39:12.698231', null, null, null, 'Activity', 'CheckpointerMain', null, null, null, '', 'checkpointer');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (null, null, 8212, null, null, '', null, null, null, '2019-11-20 06:39:12.696096', null, null, null, 'Activity', 'WalWriterMain', null, null, null, '', 'walwriter');