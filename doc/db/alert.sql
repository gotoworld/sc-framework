update t_auth_perm t set t.match_str = REPLACE(t.match_str,'pano01:','panoCat:');
update t_auth_perm t set t.match_str = REPLACE(t.match_str,'panoProj:','panoProj:');
