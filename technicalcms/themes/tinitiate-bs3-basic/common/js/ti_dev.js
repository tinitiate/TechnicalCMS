// Tinitiate JS
function breadCrumbCreator(p_url_name_list) {
    var l_return_html = "";
    var l = 0;



    for (var i = 0, len = p_url_name_list.length; i < len; i++) {

        l = i+1;
        l_return_html = l_return_html + '<li itemprop="itemListElement" itemscope itemtype="http://schema.org/ListItem">' +
                                        '<a itemprop="item" href="' + p_url_name_list[i].url + '"> <span itemprop="name">' + p_url_name_list[i].display + '</span><span itemprop="position" content="' + l + '"></span></a>' +
                                        '</li>';
    }

    l_return_html = '<ol itemscope itemtype="http://schema.org/BreadcrumbList" class="breadcrumb">' + l_return_html + '</ol>';
    return l_return_html;
}


// Generate the Side Nav Bar for the folder
function sidebarCreator(p_folder_json_data) {
    
    var l_return_html = "";
    var i;

    for(i = 0; i < p_folder_json_data.nav.length; i++) {

        l_return_html += '<a href="' + p_folder_json_data.nav[i].url + '" class="list-group-item">' + p_folder_json_data.nav[i].display + '</a>';
    }

    return '<div class="col-md-12" style="background-color:white"> \
                <br><br> \
                <div class="list-group"> \
                <a href="#" class="list-group-item active">'+ p_folder_json_data.folder.display +'</a> '
                + l_return_html +
                '</div> \
            </div>'
}
