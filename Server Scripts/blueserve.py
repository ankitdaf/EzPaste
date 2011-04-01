from bluetooth import *
import pygtk
pygtk.require('2.0')
import gtk

# get the clipboard
clipboard = gtk.clipboard_get()

server_sock = BluetoothSocket(RFCOMM)
server_sock.bind(("",0))
server_sock.settimeout(1000)
server_sock.listen(1)
port = server_sock.getsockname()[1]
uuid = "40aa695a-b295-4dff-9274-621627518ee8"

advertise_service(server_sock,"Foobar",uuid,[uuid,OBEX_OBJPUSH_CLASS],[OBEX_OBJPUSH_PROFILE])

#print "Waiting for connection on RFCOMM channel %d" % port

client_sock, client_info = server_sock.accept()
#print "Accepted connection from ", client_info

try:
    while True:
	client_sock, client_info = server_sock.accept()
        data = client_sock.recv(1024)
        if len(data) == 0: break
	clipboard.set_text(data)
	clipboard.store()
	client_sock.close()
except KeyboardInterrupt:
	client_sock.close()
	server_sock.close()
	stop_advertising(server_sock)
except IOError:
    pass
