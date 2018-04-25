package com.weile.casualgames.view.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.weile.casualgames.R;


/**
 * 自定义控件，接口方法参考AlertDialog.Builder
 *
 * @author caowy
 */
public class CustomDialog extends Dialog {

    public static interface OnDialogButtonClick {
        public void onClick(DialogInterface dialog, View view, int which);
    }

    protected int mTheme;// 主题
    protected LinearLayout mTitlePanel;// 标题栏
    protected ImageView mIconImageView;// 图标控件
    protected TextView mTitleTextView;// 标题控件
    protected CharSequence mTitleText;// 标题文字
    protected Drawable mIcon;// 图标
    protected CheckBox mCheckBox;// 单选
    protected CharSequence mCheckText;
    protected OnCheckedChangeListener mCheckListener;

    protected LinearLayout mHeaderPanel;// 扩展头部容器
    protected LinearLayout mHeaderFillPanel; // 扩展铺满头部容器
    protected LinearLayout mContentPanel;// 内容控件
    protected ScrollView mMessagePanel;
    protected TextView mMessageTextView;// 内容文字
    protected CharSequence mMessageText;

    protected View mHeaderView;

    protected View mFillHeaderView;

    protected View mCustomView;

    protected LinearLayout mButtonPanel;
    protected Button mPositiveButton;
    protected Button mNegativeButton;
    protected Button mNeutralButton;
    protected View mDividerLeftView;
    protected View mDividerRightView;
    protected CharSequence mPositiveText;
    protected CharSequence mNegativeText;
    protected CharSequence mNeutralText;
    protected OnDialogButtonClick mPositiveButtonListener;
    protected OnDialogButtonClick mNegativeButtonListener;
    protected OnDialogButtonClick mNeutralButtonListener;

    protected ListView mListView;
    protected ListAdapter mAdapter;
    protected ListAdapter mSingleChoiceAdapter;
    protected CharSequence[] mItems;
    protected OnDialogButtonClick mItemListener;
    protected int mItemResourceId = -1;
    protected int mItemTextViewId = -1;
    protected int mItemRadioId = -1;
    protected int mCheckedItemIndex;
    protected boolean mIsSingleChoice;
    protected boolean mDismissOnClick;
    protected int mLayoutWidth;
    protected int mLayoutHeight;

    protected boolean mDismissOnBtnClick = true;

    public CustomDialog(Context context, int theme) {
        super(context, theme);
        mTheme = theme;
        init();
    }

    public CustomDialog(Context context) {
        super(context);
        init();
    }

    private void init() {
        WindowManager wManager = getWindow().getWindowManager();
        mLayoutWidth = (int) (wManager.getDefaultDisplay().getWidth() * 0.8);
        mLayoutHeight = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public void show() {
        create();
        super.show();
    }

    public void setLayout(int width, int height) {
        mLayoutWidth = width;
        mLayoutHeight = height;
    }

    public void setAnimStyle(int styleId) {
        if (getWindow() != null) {
            getWindow().setWindowAnimations(styleId);
        }
    }

    /**
     * 在按钮的onclick里设置成false，该次点击不会关闭对话框，只作用一次。
     *
     * @param dismissOnBtnClick
     */
    public void setDismissOnBtnClick(boolean dismissOnBtnClick) {
        this.mDismissOnBtnClick = dismissOnBtnClick;
    }

    /**
     * Set the resource id of the icon to be used in the title.
     *
     * @param iconId
     * @return
     */
    public CustomDialog setIcon(int iconId) {
        this.mIcon = getDrawable(getContext(), iconId);
        return this;
    }

    /**
     * Set the icon to be used in the title.
     *
     * @param icon
     * @return
     */
    public CustomDialog setIcon(Drawable icon) {
        this.mIcon = icon;
        return this;
    }

    /**
     * Set the Dialog title.
     *
     * @param titleId
     * @return
     */
    public void setTitle(int titleId) {
        this.mTitleText = getText(getContext(), titleId);
    }

    /**
     * Set the Dialog title.
     *
     * @param title
     * @return
     */
    public void setTitle(CharSequence title) {
        this.mTitleText = title;
    }

    public CustomDialog setTitle(String title) {
        this.mTitleText = title;
        return this;
    }

    /**
     * Set the Dialog message.
     *
     * @param messageId
     * @return
     */
    public CustomDialog setMessage(int messageId) {
        this.mMessageText = getText(getContext(), messageId);
        return this;
    }

    /**
     * Set the Dialog message.
     *
     * @param message
     * @return
     */
    public CustomDialog setMessage(CharSequence message) {
        this.mMessageText = message;
        return this;
    }

    /**
     * 设置单选文字
     *
     * @param textId
     * @return
     */
    public CustomDialog setCheckText(int textId) {
        return setCheckText(getText(getContext(), textId));
    }

    public CustomDialog setCheckText(CharSequence text) {
        this.mCheckText = text;
        return this;
    }

    /**
     * 设置单选监听
     *
     * @param listener
     * @return
     */
    public CustomDialog setCheckListener(OnCheckedChangeListener listener) {
        this.mCheckListener = listener;
        return this;
    }

    /**
     * 获取单选状态
     *
     * @return
     */
    public boolean getChecked() {
        return mCheckBox.isChecked();
    }

    /**
     * 设置扩展头，在标题栏上面
     *
     * @param v
     */
    public CustomDialog setHeaderView(View v) {
        mHeaderView = v;
        return this;
    }

    /**
     * 设置扩展全铺的头部，在标题栏上面
     *
     * @param v
     */
    public CustomDialog setFillHeaderView(View v) {
        mFillHeaderView = v;
        return this;
    }

    /**
     * Set a custom content view for the Dialog. If a message is set, the custom
     * view will remove message view and be added to the Dialog.
     *
     * @param v
     * @return
     */
    public void setContentView(View v) {
        this.mCustomView = v;
    }

    /**
     * 设置自定义内容
     *
     * @param id
     */
    public void setContentView(int id) {
        View view = LayoutInflater.from(getContext()).inflate(id, null);
        setContentView(view);
    }

    @Deprecated
    @Override
    public void setContentView(View view, LayoutParams params) {
    }

    /**
     * Set the text to display in positive button and a listener to be invoked
     * when it is pressed.
     *
     * @param textId
     * @param listener
     * @return
     */
    public CustomDialog setPositiveButton(int textId,
                                          OnDialogButtonClick listener) {
        this.mPositiveText = getText(getContext(), textId);
        this.mPositiveButtonListener = listener;
        return this;
    }

    /**
     * Set the text to display in positive button and a listener to be invoked
     * when it is pressed.
     *
     * @param text
     * @param listener
     * @return
     */
    public CustomDialog setPositiveButton(CharSequence text,
                                          OnDialogButtonClick listener) {
        this.mPositiveText = text;
        this.mPositiveButtonListener = listener;
        return this;
    }

    /**
     * Set the text to display in negative button and a listener to be invoked
     * when it is pressed.
     *
     * @param textId
     * @param listener
     * @return
     */
    public CustomDialog setNegativeButton(int textId,
                                          OnDialogButtonClick listener) {
        this.mNegativeText = getText(getContext(), textId);
        this.mNegativeButtonListener = listener;
        return this;
    }

    /**
     * Set the text to display in negative button and a listener to be invoked
     * when it is pressed.
     *
     * @param text
     * @param listener
     * @return
     */
    public CustomDialog setNegativeButton(CharSequence text,
                                          OnDialogButtonClick listener) {
        this.mNegativeText = text;
        this.mNegativeButtonListener = listener;
        return this;
    }

    /**
     * Set the text to display in neutral button and a listener to be invoked
     * when it is pressed.*
     *
     * @param textId
     * @param listener
     * @return
     */
    public CustomDialog setNeutralButton(int textId,
                                         OnDialogButtonClick listener) {
        this.mNeutralText = getText(getContext(), textId);
        this.mNeutralButtonListener = listener;
        return this;
    }

    /**
     * Set the text to display in neutral button and a listener to be invoked
     * when it is pressed.
     *
     * @param text
     * @param listener
     * @return
     */
    public CustomDialog setNeutralButton(CharSequence text,
                                         OnDialogButtonClick listener) {
        this.mNeutralText = text;
        this.mNeutralButtonListener = listener;
        return this;
    }

    /**
     * Set a list of items to be displayed in the dialog as the content, you
     * will be notified of the selected item via the supplied listener.
     *
     * @return
     */
    public CustomDialog setItems(int itemsId,
                                 OnDialogButtonClick listener) {
        this.mItems = getTextArray(getContext(), itemsId);
        this.mItemListener = listener;
        return this;
    }

    /**
     * Set a list of items to be displayed in the dialog as the content, you
     * will be notified of the selected item via the supplied listener.
     *
     * @return
     */
    public CustomDialog setItems(CharSequence[] items,
                                 OnDialogButtonClick listener) {
        this.mItems = items;
        this.mItemListener = listener;
        return this;
    }

    /**
     * Set a list of items to be displayed in the dialog as the content, you
     * will be notified of the selected item via the supplied listener.
     *
     * @param itemsId
     * @param resourceId item layout XML name.
     * @param textviewId TextView id setup item data.
     * @param listener
     * @return
     */
    public CustomDialog setItems(int itemsId, int resourceId, int textviewId,
                                 final OnDialogButtonClick listener) {
        this.mItems = getTextArray(getContext(), itemsId);
        this.mItemListener = listener;
        this.mItemResourceId = resourceId;
        this.mItemTextViewId = textviewId;
        return this;
    }

    /**
     * Set a list of items to be displayed in the dialog as the content, you
     * will be notified of the selected item via the supplied listener.
     *
     * @param
     * @param resourceId item layout XML name.
     * @param textviewId TextView id setup item data.
     * @param listener
     * @return
     */
    public CustomDialog setItems(CharSequence[] items, int resourceId,
                                 int textviewId, final OnDialogButtonClick listener) {
        this.mItems = items;
        this.mItemListener = listener;
        this.mItemResourceId = resourceId;
        this.mItemTextViewId = textviewId;
        return this;
    }

    /**
     * Set a list of items to be displayed in the dialog as the content, you
     * will be notified of the selected item via the supplied listener. The list
     * item will have a check mark displayed for the checked item. Clicking on
     * an item in the list will not dismiss the dialog. Clicking on a button
     * will dismiss the dialog.
     *
     * @param itemsId     the resource id of an array i.e. R.array.foo
     * @param checkedItem specifies which item is checked. If -1 no items are checked.
     * @param listener    notified when an item on the list is clicked. The dialog will
     *                    not be dismissed when an item is clicked. It will only be
     *                    dismissed if clicked on a button, if no buttons are supplied
     *                    it's up to the user to dismiss the dialog.
     * @return
     */
    public CustomDialog setSingleChoiceItems(int itemsId, int checkedItem,
                                             final OnDialogButtonClick listener) {
        this.mItems = getContext().getResources().getTextArray(itemsId);
        this.mItemListener = listener;
        this.mCheckedItemIndex = checkedItem;
        this.mIsSingleChoice = true;
        return this;
    }

    /**
     * Set a list of items to be displayed in the dialog as the content, you
     * will be notified of the selected item via the supplied listener. The list
     * item will have a check mark displayed for the checked item. Clicking on
     * an item in the list will not dismiss the dialog. Clicking on a button
     * will dismiss the dialog.
     *
     * @param items       the items to be displayed.
     * @param checkedItem specifies which item is checked. If -1 no items are checked.
     * @param listener    notified when an item on the list is clicked. The dialog will
     *                    not be dismissed when an item is clicked. It will only be
     *                    dismissed if clicked on a button, if no buttons are supplied
     *                    it's up to the user to dismiss the dialog.
     * @return
     */
    public CustomDialog setSingleChoiceItems(CharSequence[] items,
                                             int checkedItem, final OnDialogButtonClick listener) {
        setSingleChoiceItems(items, checkedItem, false, listener);
        return this;
    }

    /**
     * 设置单选列表
     *
     * @param items
     * @param checkedItem    默认选中项
     * @param dismissOnClick 点击选项后关闭对话框
     * @param listener
     * @return
     */
    public CustomDialog setSingleChoiceItems(CharSequence[] items,
                                             int checkedItem, boolean dismissOnClick,
                                             final OnDialogButtonClick listener) {
        this.mItems = items;
        this.mItemListener = listener;
        this.mCheckedItemIndex = checkedItem;
        this.mIsSingleChoice = true;
        this.mDismissOnClick = dismissOnClick;
        return this;
    }

    /**
     * @param layoutId
     * @param textViewId
     * @param radioButtonId
     * @return
     */
    public CustomDialog setItemsId(int layoutId, int textViewId,
                                   int radioButtonId) {
        mItemResourceId = layoutId;
        mItemTextViewId = textViewId;
        mItemRadioId = radioButtonId;
        return this;
    }

    /**
     *
     *
     * @param items
     *            the items to be displayed.
     * @param checkedItem
     *            specifies which item is checked. If -1 no items are checked.
     * @param listener
     *            notified when an item on the list is clicked. The dialog will
     *            not be dismissed when an item is clicked. It will only be
     *            dismissed if clicked on a button, if no buttons are supplied
     *            it's up to the user to dismiss the dialog.
     *
     * @return
     */
    /**
     * Set a list of items to be displayed in the dialog as the content, you
     * should be process the item checked event in the supplied adapter.
     *
     * @param adapter
     * @param
     * @return
     */
    public CustomDialog setItems(ListAdapter adapter) {
        this.mSingleChoiceAdapter = adapter;
        return this;
    }

    public CustomDialog setItems(ListAdapter adapter,
                                 OnDialogButtonClick listener) {
        this.mSingleChoiceAdapter = adapter;
        this.mItemListener = listener;
        return this;
    }

    /**
     * Create the dialog.
     */
    public void create() {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        DialogViewContainer contentView = (DialogViewContainer) inflater
                .inflate(R.layout.dialog, (ViewGroup) getWindow()
                        .getDecorView(), false);
        // 保存动画时间
//		contentView.setEnterAnimationDuration(AppUtil
//				.isWindowAnimationEnabled(getContext()) ? getContext()
//				.getResources().getInteger(
//						android.R.integer.config_longAnimTime) : 100L);

        contentView.setEnterAnimationDuration(100L);

        setupTitlePanel(contentView); // 设置标题栏

        setupContentPanel(contentView); // 设置内容

        setupListView(contentView); // 设置列表

        setupButtonPanel(contentView); // 设置按钮

        setupHeaderView(contentView); // 设置扩展头部

        setupFillHeaderView(contentView); //设置扩展铺满头部

        setupCustomView(contentView); // 设置自定义内容

        super.setContentView(contentView);

        setupWindowParams();

//		return this;
    }

    private void setupWindowParams() {
        setAnimStyle(mTheme);
        getWindow().setLayout(mLayoutWidth, mLayoutHeight);
    }

    /**
     * Setup title panel layout.
     */
    private void setupTitlePanel(View contentView) {
        mHeaderPanel = (LinearLayout) contentView
                .findViewById(R.id.header_panel);
        mHeaderFillPanel = (LinearLayout) contentView
                .findViewById(R.id.header_fill_panel);
        mTitlePanel = (LinearLayout) contentView.findViewById(R.id.title_panel);
        mIconImageView = (ImageView) contentView.findViewById(R.id.icon);
        mTitleTextView = (TextView) contentView.findViewById(R.id.title);

        if (mIcon != null) {
            mIconImageView.setImageDrawable(mIcon);
        } else {
            mIconImageView.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mTitleText)) {
            mTitleTextView.setText(mTitleText);
        } else {
            mTitlePanel.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题文字
     *
     * @param text
     */
    public void setTitleText(String text) {
        if (!TextUtils.isEmpty(text)) {
            this.mTitleText = text;
            mTitleTextView.setText(mTitleText);
        }
    }

    /**
     * Setup content panel layout.
     */
    private void setupContentPanel(View contentView) {
        mContentPanel = (LinearLayout) contentView
                .findViewById(R.id.content_panel);
        mMessageTextView = (TextView) contentView.findViewById(R.id.message);
        mMessagePanel = (ScrollView) contentView
                .findViewById(R.id.message_panel);

        if (!TextUtils.isEmpty(mMessageText)) {
            mMessageTextView.setText(mMessageText);
        } else {
            mMessagePanel.setVisibility(View.GONE);
        }

        mCheckBox = (CheckBox) contentView.findViewById(R.id.checkbox);
        if (!TextUtils.isEmpty(mCheckText)) {
            mCheckBox.setText(mCheckText);
            mCheckBox.setOnCheckedChangeListener(mCheckListener);
        } else {
            mCheckBox.setVisibility(View.GONE);
        }
    }

    /**
     * Setup list view data.
     */
    private void setupListView(View contentView) {
        mListView = (ListView) contentView.findViewById(R.id.lv);

        IOnClickItemListener itemListener = null;
        if (mItemListener != null) {
            itemListener = new IOnClickItemListener() {

                @Override
                public void onClickItem(View view, int position) {
                    mItemListener.onClick(CustomDialog.this, view, position);
                    if (!mIsSingleChoice || mDismissOnClick) {
                        CustomDialog.this.dismiss();
                    }
                }
            };
        }

        if (mItems != null && mItems.length > 0) {
            if (!mIsSingleChoice) {
                if (mItemResourceId < 0 || mItemTextViewId < 0) {
                    mAdapter = new ItemAdapter<CharSequence>(getContext(),
                            mItems, itemListener);
                } else {
                    ItemAdapter<?> adapter = new ItemAdapter<CharSequence>(
                            getContext(), mItems, mItemResourceId,
                            mItemTextViewId, itemListener);
                    if (mItemRadioId > 0) {
                        adapter.mRadioButtonResourceId = mItemRadioId;
                    }
                    mAdapter = adapter;
                }
            } else {
                if (mSingleChoiceAdapter == null) {
                    ItemAdapter<?> adapter = new ItemAdapter<CharSequence>(
                            getContext(), mItems, mCheckedItemIndex, true,
                            itemListener);
                    if (mItemResourceId > 0) {
                        adapter.mLayoutResourceId = mItemResourceId;
                    }
                    if (mItemTextViewId > 0) {
                        adapter.mTextViewResourceId = mItemTextViewId;
                    }
                    if (mItemRadioId > 0) {
                        adapter.mRadioButtonResourceId = mItemRadioId;
                    }
                    mAdapter = adapter;
                }
            }
        }
        if (mSingleChoiceAdapter != null) {
            mAdapter = mSingleChoiceAdapter;
            if (mItemListener != null) {
                mListView.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        mItemListener
                                .onClick(CustomDialog.this, view, position);
                    }
                });
            }
        }
        mListView.setAdapter(mAdapter);
        if (mAdapter == null) {
            mListView.setVisibility(View.GONE);
        }
    }

    /**
     * Setup button panel layout.
     */
    private void setupButtonPanel(View contentView) {
        mPositiveButton = (Button) contentView.findViewById(R.id.btn_positive);
        mNegativeButton = (Button) contentView.findViewById(R.id.btn_negative);
        mNeutralButton = (Button) contentView.findViewById(R.id.btn_neutral);
        mDividerLeftView = contentView.findViewById(R.id.view_divider_left);
        mDividerRightView = contentView.findViewById(R.id.view_divider_right);
        mButtonPanel = (LinearLayout) contentView
                .findViewById(R.id.button_panel);

        boolean showPositive = false;
        boolean showNegative = false;
        boolean showNeutral = false;

        // set the confirm button visible
        if (!TextUtils.isEmpty(mPositiveText)) {
            showPositive = true;
            mPositiveButton.setText(mPositiveText);
            mPositiveButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mPositiveButtonListener != null) {
                        mPositiveButtonListener.onClick(CustomDialog.this, v,
                                DialogInterface.BUTTON_POSITIVE);
                    }
//					CustomDialog.this.dismiss();
                    if (mDismissOnBtnClick) {
                        CustomDialog.this.dismiss();
                    } else {
                        mDismissOnBtnClick = true;
                    }
                }
            });
        } else {
            mPositiveButton.setVisibility(View.GONE);
        }

        // set the cancel button visible
        if (!TextUtils.isEmpty(mNegativeText)) {
            showNegative = true;
            mNegativeButton.setText(mNegativeText);
            mNegativeButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mNegativeButtonListener != null) {
                        mNegativeButtonListener.onClick(CustomDialog.this, v,
                                DialogInterface.BUTTON_NEGATIVE);
                    }
                    if (mDismissOnBtnClick) {
                        CustomDialog.this.dismiss();
                    } else {
                        mDismissOnBtnClick = true;
                    }
                }
            });
        } else {
            mNegativeButton.setVisibility(View.GONE);
        }

        // set the neutral button visible
        if (!TextUtils.isEmpty(mNeutralText)) {
            showNeutral = true;
            mNeutralButton.setText(mNeutralText);
            mNeutralButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mNeutralButtonListener != null) {
                        mNeutralButtonListener.onClick(CustomDialog.this, v,
                                DialogInterface.BUTTON_NEUTRAL);
                    }
                    if (mDismissOnBtnClick) {
                        CustomDialog.this.dismiss();
                    } else {
                        mDismissOnBtnClick = true;
                    }
                }
            });
        } else {
            mNeutralButton.setVisibility(View.GONE);
        }

        // set the divider visible
        if (!showNegative) {
            mDividerLeftView.setVisibility(View.GONE);
            if (!showNeutral || !showPositive) {
                mDividerRightView.setVisibility(View.GONE);
            }
        } else {
            if (showNeutral != showPositive && !(showNeutral && showPositive)) {
                mDividerRightView.setVisibility(View.GONE);
            }
            if (!showNeutral && !showPositive) {
                mDividerRightView.setVisibility(View.GONE);
                mDividerLeftView.setVisibility(View.GONE);
            }
        }

        // set the button panel layout visible
        if (!showPositive && !showNegative && !showNeutral) {
            mButtonPanel.setVisibility(View.GONE);
        }
    }

    /**
     * 设置扩展头
     *
     * @param contentView
     */
    private void setupHeaderView(View contentView) {
        if (mHeaderView != null) {
            mHeaderPanel.removeAllViews();
            mHeaderPanel.setVisibility(View.VISIBLE);
            mHeaderFillPanel.setVisibility(View.GONE);
            LayoutParams lp = mHeaderView.getLayoutParams();
            if (lp == null) {
                lp = new LayoutParams(LayoutParams.FILL_PARENT,
                        LayoutParams.WRAP_CONTENT);
            }
            mHeaderPanel.addView(mHeaderView, lp);
        }
    }

    /**
     * 设置扩展铺满头
     *
     * @param contentView
     */
    private void setupFillHeaderView(View contentView) {
        if (mFillHeaderView != null) {
            mHeaderFillPanel.removeAllViews();
            mHeaderFillPanel.setVisibility(View.VISIBLE);
            mHeaderPanel.setVisibility(View.GONE);
            LayoutParams lp = mFillHeaderView.getLayoutParams();
            if (lp == null) {
                lp = new LayoutParams(LayoutParams.FILL_PARENT,
                        LayoutParams.WRAP_CONTENT);
            }
            mHeaderFillPanel.addView(mFillHeaderView, lp);
        }
    }

    /**
     * Setup custom view.
     */
    private void setupCustomView(View contentView) {
        if (mCustomView != null) {
            mContentPanel.setVisibility(View.VISIBLE);
            mContentPanel.removeAllViews();
            mContentPanel.addView(mCustomView, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        }
    }

    private CharSequence getText(Context context, int resId) {
        if (context == null) {
            return null;
        }
        try {
            return context.getText(resId);
        } catch (Exception e) {
        }
        return null;
    }

    private CharSequence[] getTextArray(Context context, int resId) {
        if (context == null) {
            return null;
        }
        try {
            return context.getResources().getTextArray(resId);
        } catch (Exception e) {

        }
        return null;
    }

    public Drawable getDrawable(Context context, int iconId) {
        if (context == null) {
            return null;
        }
        try {
            return context.getResources().getDrawable(iconId);
        } catch (Exception e) {
        }
        return null;
    }

    public ListView getListView() {
        return mListView;
    }

    public static interface IOnClickItemListener {

        void onClickItem(View view, int position);
    }

    private static class ItemAdapter<T> extends BaseAdapter {

        private static final int DEFAULT_RESOURCE_ID = R.layout.dialog_list_item;
        private static final int DEFAULT_TEXTVIEW_ID = R.id.text1;
        private static final int DEFAULT_RADIO_BUTTON_ID = R.id.radio_button;

        private int mCheckedItem = -1;
        private int mLayoutResourceId = DEFAULT_RESOURCE_ID;
        private int mTextViewResourceId = DEFAULT_TEXTVIEW_ID;
        private int mRadioButtonResourceId = DEFAULT_RADIO_BUTTON_ID;

        private boolean mIsSingleChoice;

        private T[] mItems;

        private IOnClickItemListener mListener;

        private Context mContext;

        public ItemAdapter(Context context, T[] objects,
                           IOnClickItemListener listener) {
            this(context, objects, DEFAULT_RESOURCE_ID, DEFAULT_TEXTVIEW_ID,
                    listener);
        }

        public ItemAdapter(Context context, T[] objects, int resource,
                           int textViewResourceId, IOnClickItemListener listener) {
            this.mContext = context;
            this.mLayoutResourceId = resource;
            this.mTextViewResourceId = textViewResourceId;
            this.mItems = objects;
            this.mIsSingleChoice = false;
            this.mListener = listener;
        }

        /**
         * Constructor for single choice item.
         */
        public ItemAdapter(Context context, T[] objects, int checkedItem,
                           boolean isSingleChoice, IOnClickItemListener listener) {
            this.mContext = context;
            this.mItems = objects;
            this.mCheckedItem = checkedItem;
            this.mIsSingleChoice = isSingleChoice;
            this.mListener = listener;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        mLayoutResourceId, parent, false);
            }
            TextView textView = (TextView) convertView
                    .findViewById(mTextViewResourceId);
            textView.setText(mItems[position].toString());

            View radio = convertView.findViewById(mRadioButtonResourceId);
            if (radio != null) {
                if (!mIsSingleChoice) {
                    radio.setVisibility(View.GONE);
                } else {
                    radio.setVisibility(View.VISIBLE);
                    if (radio instanceof RadioButton) {
                        if (mCheckedItem == position) {
                            ((RadioButton) radio).setChecked(true);
                        } else {
                            ((RadioButton) radio).setChecked(false);
                        }
                    }
                }
                radio.setTag(position);
                radio.setOnClickListener(mItemListener);
            }
            View rootView = convertView.findViewById(R.id.root);
            if (rootView != null) {
                rootView.setTag(position);
                rootView.setOnClickListener(mItemListener);
            }
            textView.setTag(position);
            textView.setOnClickListener(mItemListener);
            return convertView;
        }

        private View.OnClickListener mItemListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                mCheckedItem = position;
                notifyDataSetChanged();
                if (mListener != null) {
                    mListener.onClickItem(v, position);
                }
            }
        };

        @Override
        public int getCount() {
            if (mItems == null) {
                return 0;
            }
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            if (mItems == null) {
                return null;
            }
            return mItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }


    private View.OnClickListener mOnCancelClick;

    /**
     * 物理返回键事件
     */
    private void setOnKeyListener() {
        setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (mOnCancelClick != null) {
                        mOnCancelClick.onClick(null);
                        // mDialog.hide();
                        dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 设置只关闭对话框事件
     */
    public void setDismissKeyListener(final InputMethodManager inputManager) {
        setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (mOnCancelClick != null) {
                        // mDialog.hide();
                        inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                        //测试滑动卡片弹窗所需缓存字段重置
//                        ShortcutReplyDialog.isKeyBack = true;
                        dismiss();
                    }
//                    inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    dismiss();
                    return true;
                }
                return false;
            }
        });
    }

}
