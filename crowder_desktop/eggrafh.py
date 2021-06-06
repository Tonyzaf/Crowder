from tkinter import ttk
from tkinter import *
from PIL import ImageTk, Image
import sqlite3

root = Tk()

# Δημιουργία αρχικής οθόνης
style = ttk.Style(root)
root.tk.call('source', 'azure.tcl')
style.theme_use('azure')
root.title("Crowder")
root.iconbitmap("favicon.ico")
root.geometry("900x700")
root.option_add('*Font', "Arial")


root.mainloop()
