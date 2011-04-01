import bluetooth
import threading
import pygtk
pygtk.require('2.0')
import gtk

# get the clipboard
clipboard = gtk.clipboard_get()


server_sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
server_sock.bind(("",0))
server_sock.listen(1)
port = server_sock.getsockname()[1]
server_sock.settimeout(1000)
uuid = "40aa695a-b295-4dff-9274-621627518ee8"

bluetooth.advertise_service(server_sock,"Foobar",uuid,[uuid,bluetooth.OBEX_OBJPUSH_CLASS],[bluetooth.OBEX_OBJPUSH_PROFILE])

class conn_thread(threading.Thread):
	client_sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
	client_info = ""
	def __init__(self):
		threading.Thread.__init__(self)
	def run(self):
		client_sock, client_info = server_sock.accept()
		self.get()
	def get(self):
		try:
			while True:
				data = self.client_sock.recv(1024)
				if len(data) == 0 : break
				clipboard.set_text(data)
				clipboard.store()
				print data
		except IOError:
			self.client_sock.close()
			server_sock.close()
			stop_advertising(server_sock)
			pass


thrd = conn_thread()
thrd.start()
while (thrd.isAlive()):
	thrd.run()
# set the clipboard text data
#	os.popen('xclip', 'wb').write(data)

