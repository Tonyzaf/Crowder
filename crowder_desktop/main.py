import tkinter as tk
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
storage = firebase.storage()
db = firebase.database()


def login():
    # Σύνδεση
    global email, password
    Label(root, text="Email χρήστη:").pack()
    email = Entry(root, textvariable="email")
    email.pack()
    Label(root, text="").pack()
    Label(root, text="Κώδικός σύνδεσης:").pack()
    password = Entry(root, textvariable="password", show='●')
    password.pack()
    Label(root, text="").pack()
    # create Login Button
    button = Button(text="Είσοδος", height="2", width="30", bg='#FFA6F9', command=lambda: eisodos(root))
    button.pack()
    Label(root, text="").pack()
    Label(root, text="Δεν έχετε λογιαριασμό; Εγγραφείτε εδώ:", ).pack()
    button = Button(text="Εγγραφή", height="2", width="30", bg='#FFA6F9', command=lambda: eggrafh(root))
    button.pack()


def eisodos(root):
    try:
        login = auth.sign_in_with_email_and_password(email=email.get(), password=password.get())
        root.destroy()
        import arxikh
    except:
        messagebox.showwarning("Crowder", "Δεν υπάρχει αυτός ο χρήστης")


def eggrafh(root):
    root.destroy()
    import eggrafh

login()


root.mainloop()
