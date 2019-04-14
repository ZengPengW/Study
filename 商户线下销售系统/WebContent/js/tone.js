function playSound()
    {
      var borswer = window.navigator.userAgent.toLowerCase();
      if ( borswer.indexOf( "ie" ) >= 0 )
      {
        //IE内核浏览器
        var strEmbed = '<embed name="embedPlay" src="/Zpp/music/dingmisc.mp3"></embed>';
        if ( $( "body" ).find( "embed" ).length <= 0 )
          $( "body" ).append( strEmbed );
        var embed = document.embedPlay;

        //浏览器不支持 audion，则使用 embed 播放
        embed.volume = 100;
        //embed.play();这个不需要
      } else
      {
        //非IE内核浏览器
        var strAudio = "<audio id='audioPlay' src='/Zpp/music/dingmisc.mp3' hidden='true'>";
        if ( $( "body" ).find( "audio" ).length <= 0 )
          $( "body" ).append( strAudio );
        var audio = document.getElementById( "audioPlay" );

        //浏览器支持 audion
        audio.play();
      }
    }
