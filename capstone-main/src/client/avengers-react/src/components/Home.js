window.onload = function() {
    console.log("Running Tableau Script....")

    var test = document.getElementsByClassName("tableauPlaceholder");
    console.log(test);
    console.log(test.item(1));

    var divElement = document.getElementById('viz1642088807594');
    if(!(divElement === null)){
        var vizElement = divElement.getElementsByTagName('object')[0];
        if ( divElement.offsetWidth > 800 ) {
            vizElement.style.width='1000px';
            vizElement.style.height='827px';
        } else if ( divElement.offsetWidth > 500 ) { 
            vizElement.style.width='1000px';vizElement.style.height='827px';
        } else { 
            vizElement.style.width='100%';vizElement.style.height='1077px';
        }
        var scriptElement = document.createElement('script');
        scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';
        vizElement.parentNode.insertBefore(scriptElement, vizElement);

    }


        
}

function Home() {

    function tryReload( ){
        if(!localStorage.getItem('reload')){
            localStorage['reload'] = true;
            window.location.reload();
        }
    }


    return(
        <>
            <h2>Home</h2>

            <div className='tableauPlaceholder' id='viz1642088807594' >
                <object className='tableauViz'  >
                    <param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' />
                    <param name='embed_code_version' value='3' />
                    <param name='site_root' value='' />
                    <param name='name' value='mcu_stats&#47;Dashboard1' />
                    <param name='tabs' value='no' />
                    <param name='toolbar' value='yes' />
                    <param name='animate_transition' value='yes' />
                    <param name='display_static_image' value='yes' />
                    <param name='display_spinner' value='yes' />
                    <param name='display_overlay' value='yes' />
                    <param name='display_count' value='yes' />
                    <param name='language' value='en-US' />
                    <param name='filter' value='publish=yes' />
                </object>
            </div>
            {tryReload()}

        </>

    );
}
export default Home;