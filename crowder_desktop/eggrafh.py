from tkinter import ttk
from tkinter import *
from PIL import ImageTk, Image
from tkinter import messagebox
import pyrebase

root = Tk()

# Δημιουργία αρχικής οθόνης
style = ttk.Style(root)
root.tk.call('source', 'azure.tcl')
style.theme_use('azure')
root.title("Crowder")
root.iconbitmap("favicon.ico")
root.geometry("900x700")
root.option_add('*Font', "Arial")
logo = ImageTk.PhotoImage(Image.open("crowder.png"))
label = Label(image=logo)
label.pack()

#firebase
firebaseConfig = {
    "apiKey": "AIzaSyDgH0lE99lAwzlwDra4kJ2mYj1Llsbsbi8",
    "authDomain": "crowder-54fe7.firebaseapp.com",
    "databaseURL":"https://console.firebase.google.com/u/0/project/crowder-54fe7/authentication/users/",
    "projectId": "crowder-54fe7",
    "storageBucket": "crowder-54fe7.appspot.com",
    "messagingSenderId": "49190602703",
    "appId": "1:49190602703:web:73eada9ca7a6a9294f34ba"
  }
firebase = pyrebase.initialize_app(firebaseConfig)
auth = firebase.auth()


def register():
    global email, password, checkpassword
    Label(root, text="Εισάγετε όνομα χρήστη:").pack()
    username = Entry(root, textvariable="username")
    username.pack()
    Label(root, text="").pack()
    Label(root, text="Εισάγετε Email:").pack()
    email = Entry(root, textvariable="email")
    email.pack()
    Label(root, text="").pack()
    Label(root, text="Εισάγετε κωδικό σύνδεσης:").pack()
    password = Entry(root, textvariable="password", show='●')
    password.pack()
    Label(root, text="").pack()
    Label(root, text="Επαλήθευση κωδικού σύνδεσης:").pack()
    checkpassword = Entry(root, textvariable="checkpassword", show='●')
    checkpassword.pack()
    Label(root, text="").pack()
    button = Button(text="Εγγράψου!", height="2", width="30", bg='#FFA6F9', command=insert)
    button.pack()


def insert():
    if password.get() == checkpassword.get():
        try:
        #user = auth.create_user(uid=username.get(), email=email.get(), password=password.get())
            user = auth.create_user_with_email_and_password(email=email.get(), password=password.get())
            messagebox.showinfo("Crowder", "Συγχαρητήρια εγγραφήκατε!")
        except:
            messagebox.showwarning("Crowder", "To email χρησιμοποιείται ήδη")

        #user = auth.create_user(uid=username.get(), email=email.get(), password=password.get())
    elif password.get() != checkpassword.get():
        messagebox.showwarning("Crowder", "Οι κωδικοί που εισάγατε διαφέρoυν μεταξύ τους")


register()


root.mainloop()
