delete project, project_hierarchy, project_version from project
 left join project_hierarchy on project_hierarchy.child_id = project.id
 left join project_version on project_version.id = project.id