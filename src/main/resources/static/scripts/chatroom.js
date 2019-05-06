var stompClient = null;
var roomId = null;
var disconnectedMessageShown = false;

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  }
  else {
    $("#conversation").hide();
  }
  $("#greetings").html("");
  // disconnectedMessageShown = false;
}

function connect() {
  stompClient = new StompJs.Client({
    brokerURL: '',
    onConnect: function(frame) {
      setConnected(true);
      roomId = $("meta[name='chatroom']").attr('value');
      console.log('Connected: ' + frame);
      stompClient.subscribe('/chat/' + roomId, function(message) {
        appendMessage(JSON.parse(message.body));
      });
    },
    webSocketFactory: function() {
      return new SockJS("/gs-guide-websocket");
    },
    onWebSocketClose: function() {
      if (!disconnectedMessageShown) {
        appendMessage({
          senderName: "System",
          body: "Disconnected from server",
        });
        disconnectedMessageShown = true;
      }
  }
  });
  stompClient.activate();
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.deactivate();
  }
  setConnected(false);
  console.log("Disconnected");
}

function sendMessage(content) {
  stompClient.publish({
    destination: "/chat/" + roomId + "/send-message",
    body: JSON.stringify({'body': content}),
  });
}

function appendMessage(message) {
  $("#conversation").append("<tr><td>" + message.senderName + ":</td><td>" + message.body + "</td></tr>");
}

$(function() {
  $("form").on('submit', function(e) {
    e.preventDefault();
  });
  $("send-message").on('submit', function(e) {
    e.preventDefault();
  });
  $("#connect").click(function() {
    connect();
  });
  $("#disconnect").click(function() {
    disconnect();
  });
  $("#send").click(function() {
    var content = $("#send-message :input[name='message-body']").val()
    sendMessage(content);
  });
});